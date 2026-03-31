import java.util.ArrayList;
import java.util.List;

public class SuppressWarningsExample {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add("Java");
		list.add(123);
		list.add(45.67);

		for (Object obj : list) {
			System.out.println(obj);
		}
	}
}
