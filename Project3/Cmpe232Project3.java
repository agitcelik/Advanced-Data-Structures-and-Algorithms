public class Cmpe232Project3 {
	public static void Extended_Bottom_Up_Cut_Rod(int p[], int n) {

		int r[] = new int[n + 1];
		int s[] = new int[n + 1];
		r[0] = 0;
		for (int j = 1; j <= n; j++) {
			int q = Integer.MIN_VALUE;
			for (int i = 1; i <= j; i++) {
				if (q < (p[i] + r[j - i])) {
					q = p[i] + r[j - i];
					s[j] = i;
				}
			}
			r[j] = q;
		}

		while (n > 0) {
			System.out.print(s[n] + "  ");
			n = n - s[n];
		}
	}

	public static int CUT_ROD_Bottom_UP2(int[] priceTable, int lenght, int costOfEachCutting) {
		int[] r = new int[lenght + 1];
		r[0] = 0;
		for (int j = 1; j <= lenght; j++) {
			int q = -1;
			for (int i = 0; i < j; i++) {
				// if(i==j-1){
				// q=Math.max(q,p[i]+r[j-(i+1)]);
				// }
				// else {
				if (priceTable[i] + r[j - (i + 1)] <= priceTable[j - 1]) {
					q = Math.max(q, priceTable[i] + r[j - (i + 1)]);

				} else {
					q = Math.max(q, (priceTable[i] + r[j - (i + 1)]) - costOfEachCutting);
				}

			}
			r[j] = q;
			System.out.println(q);
		}
		return r[lenght];
	}

	public static int Cut_Rod_Bottom_Up(int[] price, int length, int costOfEachCutting) {
		int revenue[] = new int[length + 1];
		revenue[0] = 0;
		for (int j = 1; j <= length; j++) {
			int q = Integer.MIN_VALUE;//
			for (int i = 0; i < j; i++) {
				if (i+1==j) {// (i == j - 1)
					q = Math.max(q, price[i] + (revenue[j - (i + 1)]));
				}
				// count++;
				else {
					q = Math.max(q, price[i] + (revenue[j - (i + 1)]) - costOfEachCutting);//due to i=0 (add +1)
					// System.out.println(count);
				}
			}
			revenue[j] = q;
			System.out.print(q + " ");
		}

		return revenue[length];
	}

	public static void main(String[] args) throws Exception {
		
			int price1[] = { 5, 6, 9, 12};
			int price2[] = { 0, 1, 1, 2 };
			int price3[] = { 1,1,2,3 };
			int price4[] = { 5, 6, 9, 12 };
			// System.out.println(price.length);
			// int price2 [] ={0,1,1,2};
			int length1 = 4;
			int length2 = 4;
			int length3 = 4;
			int length4 = 4;
			int cost = 1;
			try {
			System.out.print("/[" + Cut_Rod_Bottom_Up(price1, length1, cost) + "==>revenue of array with cost]");
			System.out.println("\n");
		
			// System.out.println(Bottom_UP_CUT_ROD(price, length, cost));
			// Extended_Bottom_Up_Cut_Rod(price,4);
			// System.out.print(BottomUpCutRod(price, length,cost));
		} catch (Exception e) {
			System.out.println("length should not be bigger than the length of price");
		}
			try{
			System.out.print("/[" + Cut_Rod_Bottom_Up(price2, length2, cost) + "==>revenue of array with cost]");
			System.out.println("\n");
	} catch (Exception e) {
		System.out.println("length should not be bigger than the length of price");
	}
			try{
			System.out.print("/[" + Cut_Rod_Bottom_Up(price3, length3, cost) + "==>revenue of array with cost]");
			System.out.println("\n");
			} catch (Exception e) {
				System.out.println("length should not be bigger than the length of price");
			}
			
			try{
				System.out.print("/[" + Cut_Rod_Bottom_Up(price4, length4, cost) + "==>revenue of array with cost]");
			} catch (Exception e) {
				System.out.println("length should not be bigger than the length of price");
			}
	}
}
