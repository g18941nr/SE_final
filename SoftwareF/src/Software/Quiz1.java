package Software;

public class Quiz1 {
	public static void main (String [] args) {
		int [] score_0, score_1, score_2;
		score_0 = new int [3];
		score_1 = new int [3];
		score_2 = new int [3];

		score_0 [0] = 50;
		score_0 [1] = 60;
		score_0 [2] = 70;

		score_1 [0] = 100;
		score_1 [1] = 20; //下二桁10の人（10+10=20）
		score_1 [2] = 80;

		score_2 [0] = 80;
		score_2 [1] = 80;
		score_2 [2] = 50;

		//平均値を計算し、表示する(Quiz1-(i))
		double [] averageArr = new double [3];
		averageArr[0] = calcAverage(score_0);
		averageArr[1] = calcAverage(score_1);
		averageArr[2] = calcAverage(score_2);
		
		int[] Kamoku0, Kamoku1, Kamoku2;
		//aveKamoku1 = {score_0[0], score_1[0], score_2[0]};
		Kamoku0 = new int[3];
		Kamoku1 = new int[3];
		Kamoku2 = new int[3];
		
		Kamoku0[0] = score_0[0];
		Kamoku0[1] = score_1[0];
		Kamoku0[2] = score_2[0];
		
		Kamoku1[0] = score_0[1];
		Kamoku1[1] = score_1[1];
		Kamoku1[2] = score_2[1];
		
		Kamoku2[0] = score_0[2];
		Kamoku2[1] = score_1[2];
		Kamoku2[2] = score_2[2];
		
		double[] aveKamoku = new double[3];
		aveKamoku[0] = calcAverage(Kamoku0);
		aveKamoku[1] = calcAverage(Kamoku1);
		aveKamoku[2] = calcAverage(Kamoku2);
		
		double sd0 = standardDeviation(aveKamoku[0], score_0);
		double sd1 = standardDeviation(aveKamoku[1], score_1);
		double sd2 = standardDeviation(aveKamoku[2], score_2);
		
		double[] sd = { sd0, sd1, sd2 };		
		
		System.out.println("0番目の人の平均点 " + averageArr[0]);
		System.out.println("1番目の人の平均点 " + averageArr[1]);
		System.out.println("2番目の人の平均点 " + averageArr[2]);
		
		System.out.println("科目0の平均点："+aveKamoku[0]);
		System.out.println("科目1の平均点："+aveKamoku[1]);
		System.out.println("科目2の平均点："+aveKamoku[2]);

		//偏差値
		System.out.println("0番目の人");
		for (int i = 0; i < score_0.length; i++) {
			double dv = deviationValue(sd[0], aveKamoku[0], score_0[i]);
			System.out.println("科目" + i + "の偏差値 " + dv);
		}
		System.out.println("1番目の人");
		for (int i = 0; i < score_1.length; i++) {
			double dv = deviationValue(sd[1], aveKamoku[1], score_1[i]);
			System.out.println("科目" + i + "の偏差値 " + dv);
		}
		System.out.println("2番目の人");
		for (int i = 0; i < score_2.length; i++) {
			double dv = deviationValue(sd[2], aveKamoku[2], score_2[i]);
			System.out.println("科目" + i + "の偏差値 " + dv);
		}

		//平均値が最高点、最低点の人をそれぞれ判定し、表示する(Quiz1-(ii))
		int id_maxScore = searchPersonWithMaxScore(averageArr);
		int id_minScore = searchPersonWithMinScore(averageArr);

		System.out.println("平均値が最高点の人 "+ id_maxScore);
		System.out.println("平均値が最低点の人 "+ id_minScore);
	}


	//Quiz1 - (i)
	//平均値を計算するメソッド
	//引数：試験の点数を格納したint型の配列
	//返り値：平均点（double型）	
	public static double calcAverage(int [] score) {
		int num = score.length;
		double average = 0.;
		for(int i=0;i<num;i++) {
			average += score[i];			
		}

		average /= (double)num;

		return average;		
	}

	// Quiz1 - (ii)
	//平均値が最高点の人を判定するメソッド
	//引数：0, 1, 2番の人の平均値を格納した配列
	//返り値：最高点を持つ人のID（0, 1, 2のいずれか）
	public static int searchPersonWithMaxScore (double[] averages) {
		double maxScore = 0.;
		int stu_id = 0;
		int num = averages.length;

		for(int i=0;i<num;i++) {
			if(maxScore < averages[i]) {
				maxScore = averages[i];
				stu_id = i;				
			}			
		}		
		return stu_id;		
	}

	// Quiz1 - (ii)
	//平均値が最低点の人を判定するメソッド
	//引数：0, 1, 2番の人の平均値を格納した配列
	//返り値：最低点を持つ人のID（0, 1, 2のいずれか）
	public static int searchPersonWithMinScore (double[] averages) {
		double minScore = 100.;
		int stu_id = 0;
		int num = averages.length;

		for(int i=0;i<num;i++) {
			if(minScore > averages[i]) {
				minScore = averages[i];
				stu_id = i;				
			}			
		}		
		return stu_id;		
	}
	 
	//標準偏差を計算する
	public static double standardDeviation(double aveKamoku, int[] score) {
		double sum = 0;
		for(int i=0; i<score.length; i++) {
			sum += (score[i] - aveKamoku) * (score[i] - aveKamoku);
		}
		double bunsan = sum / score.length;
		double sd = Math.sqrt(bunsan);
		return sd;
	}
	 //偏差値を計算する
	public static double deviationValue(double sd, double ave, int score) {
		double dv = (score - ave)*10 / sd + 50;
		return dv;
		
	}
	
}
