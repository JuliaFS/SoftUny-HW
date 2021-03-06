package WebCrawler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter url: ");
		String urlString = scanner.nextLine();
		System.out.println("enter depth: ");
		int depth = scanner.nextInt();
		HashSet<String> crawledUrls = new HashSet<String>();
		try (PrintWriter writer = new PrintWriter("crawled-urls.txt", "UTF-8")) {
			crawlUrl(urlString, crawledUrls, depth, writer);
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
	}
	
	private static void crawlUrl(String _url, HashSet<String> crawledUrls, int depth, PrintWriter writer) {
		if (depth <= 0) {
			return;
		}
		
		if (crawledUrls.contains(_url)) {
			return;
		} else {
			crawledUrls.add(_url);
		}
		
		writer.println(_url);
		
		URL url;
		try {
			url = new URL(_url);
		} catch (MalformedURLException e) {
			return;
		}
		
		String siteContents = getUrlContents(url);
		if (siteContents != null) {
			ArrayList<String> links = getLinks(siteContents);
			links.parallelStream().forEach(link -> {
				String path = link;
				if (link.indexOf("http") < 0) {
					path = appendHost(link, url);
				}
				crawlUrl(path, crawledUrls, depth - 1, writer);

			});
		}
	}

	private static String appendHost(String link, URL url) {
		return url.getProtocol() + "://" + url.getHost() + link;
	}

	private static ArrayList<String> getLinks(String siteContents) {
		Matcher p = Pattern.compile("<a[^>]*? href=\"(?<url>[^\"]+)\"[^>]*?>(?<text>.*?)</a>", Pattern.CASE_INSENSITIVE).matcher(siteContents);
		
		ArrayList<String> links = new ArrayList<String>();
		while (p.find()) {
			String link = p.group(1);
			links.add(link);
		}
		
		return links;
	}

	private static String getUrlContents(URL url) {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			String contentType = con.getContentType();
			if (contentType != null) {
				if (contentType.indexOf("html") > 0) {
					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					
					return response.toString();
				}
			
			}
		} catch (IOException e) {
			
		} finally {
			con.disconnect();
		}
		
		return null;
	}
}
