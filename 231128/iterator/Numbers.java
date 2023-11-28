package iterator;

public class Numbers implements Aggregate {
	
	int[] numbers = new int[] {1,2,3,4,5};
	//Aggregate은 집합체이므로 집합체만 있으면 끝
	
	@Override
	public Object iterator() {
		return new NumbersIterator(numbers);
		// NumbersIterator에 {1,2,3,4,5} 배열을 넘김
	}
}
