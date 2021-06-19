import java.io.*;
import java.math.BigInteger;

public class Main {
	private static BigInteger[][] values;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String arr[] = br.readLine().split(" ");
		int n = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		
		values = new BigInteger[n+1][m+1];
		
		bw.write(String.valueOf(com(n,m)));
		bw.flush();
	}
	
	public static BigInteger com(int x, int y) {
		if(x == y || y == 0) return new BigInteger("1");
		if(values[x][y] != null) return values[x][y];
		values[x][y] = new BigInteger("0");
		values[x][y] = values[x][y].add(com(x-1,y-1)).add(com(x-1,y));
		return values[x][y];
	}
}
