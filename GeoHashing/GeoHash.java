package systemDesign;

import java.util.ArrayList;
import java.util.List;

public class GeoHash {
	private static final String map = "0123456789bcdefghjkmnpqrstuvwxyz";
	public String encode(double latitude, double longitude, int precision) {
		int len = (precision * 5) % 2 == 1 ? (precision * 5) / 2 + 1 : (precision * 5) / 2;
		
		String longEncode = encodeGeneration(longitude, -180, 180, len);
		String latEncode = encodeGeneration (latitude, -90, 90, len);
		String merged = merge(longEncode, latEncode);
		return getResult(merged);
	}

	private String encodeGeneration(double target, double left, double right, int steps) {
		StringBuilder sb = new StringBuilder();
		while (steps-- > 0) {
			double mid = left + (right - left) / 2;
			if (mid <= target) {
				left = mid;
				sb.append('1');
			} else {
				right = mid;
				sb.append('0');
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String merge(String longi, String lati) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < longi.length()) {
			sb.append(longi.charAt(i));
			sb.append(lati.charAt(i));
			i++;
		}
		return sb.toString();
	}

	private String getResult(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i = i + 5) {
			int num = 0;
			for (int j = i; j < i + 5; j++) {
				num = 2 * num + str.charAt(j) - '0';
			}
			sb.append(map.charAt(num));
		}
		return sb.toString();
	}
	
	private List<String> geiNeighbors(String str) {
		int[][] dirs = {{1, 0},  {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}, {1, - 1}, {1, 1}};
		List<String> res = new ArrayList<>();
		for(int[] dir: dirs) {
			res.add(getNeighbor(str, dir));
		}
		return null;
	}
	
	private String getNeighbor(String geohash, int[] dir) {
		int dx = dir[0];
		int dy = dir[1];
		long loc = 0;
		for(int i = 0; i < geohash.length(); i++) {
			int index = map.indexOf(geohash.charAt(i));
			for(int j = 4; j >= 0; j--) {
				loc = 2 * loc + ((index & (1 << j)) > 0 ? 1: 0);
			}
		}
		loc += 2 * dx;
		loc += dy;
		String str = Long.toBinaryString(loc);
		System.out.println( getResult(str));
		return getResult(str);
	}
	
	
	public static void main(String[] args) {
		GeoHash sol = new GeoHash();
		System.out.println(sol.encode(39.92816697, 116.38954991, 10));
		sol.geiNeighbors("wx4g0s8q3j");
	}
}
