import java.util.Random;

public class MySudokuGen {
	static int[] holesChecked;
	public static void generate(){//生成数独题目
		int outerIndex;		//以相邻的三行和三列为一组
		int index;			//组内序号
		int indexA,indexB;	//实际序号
		int times;			//进行打乱操作的次数
		Random rand=new Random();
		times=rand.nextInt(10)+20;	//打乱操作次数
		for (int i = 0; i < times; i++){
			outerIndex=rand.nextInt(3);	//组件序号
		    index=rand.nextInt(3);	//组内序号
		    indexA=outerIndex*3+index;	//实际序号
		    indexB=outerIndex*3+(index+1)%3;//实际序号
	        switch(rand.nextInt(6)){
	        case 0: 
	        	SwapRow(indexA,indexB);	//交换行（组内）
	        	//System.out.println("SwapRow("+indexA+","+indexB+")");
	        	break;
	        case 1: 
	        	SwapCol(indexA,indexB);	//交换列（组内）
	        	//System.out.println("SwapCol("+indexA+","+indexB+")");
	        	break;
	        case 2: 
	        	SwapBigRow(outerIndex);	//行整组交换
	        	//System.out.println("SwapBigRow("+outerIndex+")");
	        	break;
	        case 3: 
	        	SwapBigCol(outerIndex);	//列整组交换
	        	//System.out.println("SwapBigCol("+outerIndex+")");
	        	break;
	        default://数字交换的概率更大
	        	SwapDigits();			//交换数字
	        	//System.out.println("SwapDigits()");
	        	break;
	        }
	        
	    }
		Constant.answer=new int[9][9];
	    for(int i=0;i<9;i++){
	    	for(int j=0;j<9;j++){
	    		Constant.answer[i][j]=Constant.a[i][j];
	    	}
	    }
		//Constant.printA();
	    DigHole();
		for(int k=0;k<9;k++){
			for(int j=0;j<9;j++){
				System.out.print(Constant.a[k][j]);
			}
			System.out.print('\n');
		}
		System.out.print('\n');
	}
	
	private static void SwapDigits(){
		Random rand=new Random();
		int digit1,digit2;
		digit1=rand.nextInt(9);
		while((digit2=rand.nextInt(9))==digit1);
		digit1++;
		digit2++;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(Constant.a[i][j]==digit1)Constant.a[i][j]=digit2;
				else if(Constant.a[i][j]==digit2)Constant.a[i][j]=digit1;
			}
		}
		
	}
	private static void SwapRow(int RowA,int RowB)
	{
		
	    int[] temp=Constant.a[RowA];
	    Constant.a[RowA]=Constant.a[RowB];
	    Constant.a[RowB]=temp;
	}

	private static void SwapCol(int ColA,int ColB)
	{
	    int temp=0;
	    for(int i=0;i<9;i++)
	    {
	        temp=Constant.a[i][ColA];
	        Constant.a[i][ColA]=Constant.a[i][ColB];
	        Constant.a[i][ColB]=temp;
	    }
	}

	private static void SwapBigRow(int outerIndex)
	{
	    int RowA=outerIndex;
	    int RowB=(outerIndex+1)%3;
	    SwapRow(RowA*3,RowB*3);
	    SwapRow(RowA*3+1,RowB*3+1);
	    SwapRow(RowA*3+2,RowB*3+2);
	}

	private static void SwapBigCol(int outerIndex)
	{
	    int ColA=outerIndex;
	    int ColB=(outerIndex+1)%3;
	    SwapCol(ColA*3,ColB*3);
	    SwapCol(ColA*3+1,ColB*3+1);
	    SwapCol(ColA*3+2,ColB*3+2);
	}
	
	private static void DigHole() {
		// TODO Auto-generated method stub
		Constant.holes=0;
		int direct=1;	//跳格遍历的方向
		int[] blockHoles={0,0,0,0,0,0,0,0,0};	//每一区块内的洞的数量
		holesChecked=new int[2*Constant.Diffculty.holesTotal[Constant.dfct+1]];	//洞的坐标
		boolean[][] checked=new boolean[9][9];	//点是否被检查过
		int checkedCount;	//被检查过的点的个数
		Random rand=new Random();
		int x,y;
		int maxHoles=rand.nextInt(	//洞的数量上限
				Constant.Diffculty.holesTotal[Constant.dfct+1]-
				Constant.Diffculty.holesTotal[Constant.dfct])
				+Constant.Diffculty.holesTotal[Constant.dfct];
		x=rand.nextInt(9);
		y=rand.nextInt(9);
		for(Constant.holes=0,checkedCount=0;Constant.holes<maxHoles&&checkedCount<=81;){
			int centerNo=getCenter(x)/3*3+getCenter(y)/3;	//获得该区块的编号
			if(blockHoles[centerNo]<Constant.Diffculty.holesEach[Constant.dfct+1]){
				//每个区块的数量未达到上限则继续
				if(!checked[x][y]){	//没被检查过
					if(checkUnique(x,y)){	//检查是否产生唯一解
						Constant.a[x][y]=0;
						holesChecked[Constant.holes*2]=x;	//存储洞的坐标
						holesChecked[Constant.holes*2+1]=y;
						Constant.holes++;
						blockHoles[centerNo]++;
						/*System.out.println("Checked:"+x+" "+y+" "+holesChecked[Constant.holes*2]+" "+
								holesChecked[Constant.holes*2+1]+Constant.holes);*/
					}
					checked[x][y]=true;	//标记检查
					checkedCount++;
				}
			}
			int sel=rand.nextInt(5);//为更均匀地产生洞，五分之一的概率重新随机
			if(sel==0){
				x=rand.nextInt(9);
				y=rand.nextInt(9);
			}else{
				switch(Constant.dfct){
				case Constant.Diffculty.Easy://简单模式全随机
					x=rand.nextInt(9);
					y=rand.nextInt(9);
					break;
				case Constant.Diffculty.Medium://中等模式跳格遍历
					direct=(x+y)%2==0?1:-1;
					if(y!=8&&y!=1)
						y=(x%2)==0?(y+2*direct):(y-2*direct);
					else{
						y-=1;
						x+=1;
					}
					if(x>8){
						direct=-direct;
						x-=9;
					}
					break;
				case Constant.Diffculty.Hard://困难模式之字形访问
					if(y==8&&x%2==0){	//到头则拐弯，进入下一行
						x++;
					}else if(y==0&&x%2==1){ //到头则拐弯，进入下一行
						x++;
					}else{
						y=x%2==0?y+1:y-1;	//向左或向右
					}
					if(x>8){
						x=rand.nextInt(9);
						y=rand.nextInt(9);
					}
					break;
				case Constant.Diffculty.Evil://变态模式循环遍历
					if(y==8)
						x=(x+1)%9;
					y=(y+1)%9;
					break;
				default:break;
				}
			}
		}
		for(int i=0;i<Constant.holes;i++){
			Constant.a[holesChecked[i*2]][holesChecked[i*2+1]]=0;
		}
		Constant.printA();
	}
	private static void DigHole2() {
		// TODO Auto-generated method stub
		Constant.holes=0;
		int direct=1;
		int[] blockHoles={0,0,0,0,0,0,0,0,0};
		holesChecked=new int[2*Constant.Diffculty.holesTotal[Constant.dfct+1]];
		boolean[][] checked=new boolean[9][9];
		int checkedCount;
		Random rand=new Random();
		int x,y;
		int maxHoles=rand.nextInt(
				Constant.Diffculty.holesTotal[Constant.dfct+1]-
				Constant.Diffculty.holesTotal[Constant.dfct])
				+Constant.Diffculty.holesTotal[Constant.dfct];
		if(Constant.dfct==Constant.Diffculty.Easy){
			x=rand.nextInt(9);
			y=rand.nextInt(9);
		}else{
			x=0;y=0;
		}
		for(Constant.holes=0,checkedCount=0;Constant.holes<maxHoles&&checkedCount<=81;){
			int centerNo=getCenter(x)/3*3+getCenter(y)/3;
			if(blockHoles[centerNo]<Constant.Diffculty.holesEach[Constant.dfct+1]){
				if(!checked[x][y]){
					if(checkUnique(x,y)){
						Constant.a[x][y]=0;
						holesChecked[Constant.holes*2]=x;
						holesChecked[Constant.holes*2+1]=y;
						Constant.holes++;
						blockHoles[centerNo]++;
						/*System.out.println("Checked:"+x+" "+y+" "+holesChecked[Constant.holes*2]+" "+
								holesChecked[Constant.holes*2+1]+Constant.holes);*/
					}
					checked[x][y]=true;
					checkedCount++;
				}
			}
			switch(Constant.dfct){
			case Constant.Diffculty.Easy:
				x=rand.nextInt(9);
				y=rand.nextInt(9);
				break;
			case Constant.Diffculty.Medium:
				direct=(x+y)%2==0?1:-1;
				if(y!=8&&y!=1)
					y=(x%2)==0?(y+2*direct):(y-2*direct);
				else{
					y-=1;
					x+=1;
				}
				if(x>8){
					//direct=-direct;
					x-=9;
				}
				break;
			case Constant.Diffculty.Hard:
				if(y==8&&x%2==0){
					x++;
				}else if(y==0&&x%2==1){
					x++;
				}else{
					y=x%2==0?y+1:y-1;
				}
				if(x>8){
					x=rand.nextInt(9);
					y=rand.nextInt(9);
				}
				break;
			case Constant.Diffculty.Evil:
				if(y==8)
					x=(x+1)%9;
				y=(y+1)%9;
				break;
			default:break;
			}
		}
		for(int i=0;i<Constant.holes;i++){
			Constant.a[holesChecked[i*2]][holesChecked[i*2+1]]=0;
		}
		Constant.printA();
	}
	private static boolean checkUnique(int x,int y){	//挖孔后,检查解的唯一性
		int tmp=Constant.a[x][y];
		for(int i=1;i<=9;i++){
			if(i!=tmp){	//用不等于原来的数字替换，若成功则证明不唯一
 				if(checkOne(x,y,i)){	//如果填在这里不产生冲突
					Constant.a[x][y]=i;	//则开始检验
					if(check(0)){	//检查是否有解
						Constant.a[x][y]=tmp;
						return false;
					}
				}
			}
		}
		Constant.a[x][y]=0;	//若唯一，则挖孔
		return true;
	}
	private static boolean check(int index){	//从第index个洞开始检查是否有解
		if(index<Constant.holes){	//若没填完则继续填，否则返回真
			for(int i=1;i<=9;i++){	//将每种可能代入试解
				if(checkOne(holesChecked[index*2],holesChecked[index*2+1],i)){	//若能填
					Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=i;//则填
					if(check(index+1)){	//并开始填下一个空, 若返回真则证明能填到最后一个
						Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=0;//恢复原始状态
						return true;
					}
				}
			}
			Constant.a[holesChecked[index*2]][holesChecked[index*2+1]]=0;//恢复原始状态
			return false;
		}
		System.out.println("ok");
		Constant.printAnswer();
		Constant.printA();
		return true;
	}
	public static boolean checkOne(int current_i, int current_j, int num) {// 检查(i,j)位的num在矩阵中是否合法
		for (int i = 0; i < 9; i++) {// 第一步检查所在行
			if (Constant.a[i][current_j] == num) {
				return false;
			}
		}
		for (int j = 0; j < 9; j++) {// 第二步检查所在列
			if (Constant.a[current_i][j] == num) {
				return false;
			}
		}
		// 第三步检查所在的小方形
		int center_i = getCenter(current_i);
		int center_j = getCenter(current_j);
		if(Constant.a[center_i - 1][center_j - 1]==num||
				Constant.a[center_i][center_j - 1]==num||
				Constant.a[center_i + 1][center_j - 1]==num||
				Constant.a[center_i - 1][center_j]==num||
				Constant.a[center_i][center_j]==num||
				Constant.a[center_i + 1][center_j]==num||
				Constant.a[center_i - 1][center_j + 1]==num||
				Constant.a[center_i][center_j + 1]==num||
				Constant.a[center_i + 1][center_j + 1]==num)
			return false;

		return true;
	}

	private static int getCenter(int n) {
		return n/3*3+1;
	}
}
