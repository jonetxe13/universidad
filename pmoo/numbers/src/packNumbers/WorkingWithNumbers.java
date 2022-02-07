package packNumbers;

public class WorkingWithNumbers {
	public boolean isEven(int number){
		if(number % 2 == 0) return true;
        return false;
	}
	public void evenValues(int number) {
		for(int i = 1; i <= number; i++){
			if (isEven(i)) System.out.println(i);
		}
	}
}
