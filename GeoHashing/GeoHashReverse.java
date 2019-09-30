package systemDesign;

public class GeoHashII {
	public double[] decode(String geohash) {
		String binaryGeoHash = getBinaryGeoHash(geohash);
		 
        // step 2: get binary representation of the long and latitude
        String[] binaryLongLati = getBinaryLongAndLati(binaryGeoHash);
 
        double longtitude = getLocation(binaryLongLati[0], -180, 180);
        double latitude = getLocation(binaryLongLati[1], -90, 90);
        System.out.println(latitude);
        System.out.println(longtitude);
        return new double[] {latitude, longtitude};
	}
	
	private String getBinaryGeoHash(String geohash) {
		String map = "0123456789bcdefghjkmnpqrstuvwxyz";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < geohash.length(); i++) {
			int index = map.indexOf(geohash.charAt(i));
			for(int j = 4; j >= 0; j--) {
				sb.append((index & (1 << j)) > 0 ? 1: 0);
			}
		}
		return sb.toString();
	}
	
	private String[] getBinaryLongAndLati(String str) {
		StringBuilder longi = new StringBuilder();
		StringBuilder lati = new StringBuilder();
		for(int i = 0; i < str.length(); i = i + 2) {
			longi.append(str.charAt(i));
			if(i + 1 == str.length()) break;
			lati.append(str.charAt(i + 1));
		}
		return new String[] {longi.toString(), lati.toString()};
	}
	
	private double getLocation(String str, double left, double right) {
		double mid = left + (right - left) / 2;	
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '0') {
				right = mid;
			} else {
				left = mid;
			}
			mid = left + (right - left) / 2;
		}
		return mid; 
	}
	
	public static void main(String[] args) {
		GeoHashII sol = new GeoHashII();
		sol.decode("wx4g0s");
	}
}
