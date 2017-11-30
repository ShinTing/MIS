//*HW2*//
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Random;

public class MIS {

	public static void main(String[] args) {
		int[][] neighbor = {{1, 5}, 
			 	   {0, 2, 4}, 
			 	   {1, 3, 4},
			 	   {2, 4},
			 	   {1, 2, 3, 5, 6},
			 	   {0, 4}, 
			 	   {4}};
		boolean[] i = new boolean[7]; // independent set
		boolean[] g = new boolean[7]; // graph
		boolean[] nothing = new boolean[7]; // 空的
		Random ran = new Random();
		int cont; // 計算set大小
		int v;
		ArrayList<String> iType = new ArrayList<String>(); //動態array
		String independentset;
		int[] iCount = new int[100];
		Arrays.fill(iCount, 0);
		
		for (int j = 0; j < 1000; j++ ) // 1000次
		{
			// 初始值
			cont = 0;
			independentset = "";
			Arrays.fill(i, false); 
			Arrays.fill(g, true);
			Arrays.fill(nothing, false);
			
			
			while (!Arrays.equals(g, nothing)) // 還有點沒做判斷
			{
				v = ran.nextInt(7); // 隨機取一點
				if (g[v])
				{
					i[v] = true; // add independent set
					g[v] = false;
					for (int k = 0; k < neighbor[v].length; k++)
					{
						g[neighbor[v][k]] = false;
					}
				}
			}
			
			System.out.print("MIS:{");
			independentset += "MIS:{"; // 放入字串
			for (int x = 0; x < i.length; x++)
			{
				if (i[x])
				{
					cont++;
				}	
			}
			
			for (int x = 0; x < i.length; x++)
			{
				if (i[x])
				{
					if (cont != 1)
					{
						System.out.printf("%d,", x);
						independentset += x+","; // 放入字串
						cont--;
					}
					else
					{
						System.out.printf("%d", x);	
						independentset += x; // 放入字串
					}
				}
			}
			System.out.print("}\n");
			independentset += "}";
			
			// 計算種類
			int size = iType.size();
			for (int k = 0; k <= size; k++)
			{
				
				if (size == 0) // || independentset != iType.get(k)) // 如果有新的type
				{
					iType.add(independentset); // 新增
					iCount[k]++; // 次數多一
					System.out.print("1.k"+k+"="+iCount[k]+","+independentset+"\n");
				}
				else if (k != size && iType.get(k).equals(independentset))
				{
					iCount[k]++; // 次數多一
					System.out.print("2.k"+k+"="+iCount[k]+","+independentset+"\n");
					break;
				}
				else if (k == size)
				{
					iType.add(independentset); // 新增
					iCount[k]++; // 次數多一
					System.out.print("3.k"+k+"="+iCount[k]+","+independentset+"\n");
				}
				System.out.print("4.k"+k+"="+iCount[k]+","+independentset+","+iType.get(k)+"\n");
			}
		}
		
		// 印出種類
		System.out.print("\n出現的次數總計\n");
		for (int k = 0; k < iType.size(); k++)
		{
			System.out.printf("%s:", iType.get(k)); // type of set
			System.out.printf("%d次\n", iCount[k]);
		}	
	}
}
