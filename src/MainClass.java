import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		ArrayList<String[]> dataSet = new ArrayList<>();

		dataSet.add(new String[] { "A", "B", "C" });
		dataSet.add(new String[] { "B" });
		dataSet.add(new String[] { "D", "B", "C" });
		dataSet.add(new String[] { "D", "A", "C" });
		dataSet.add(new String[] { "E", "C" });
		dataSet.add(new String[] { "A", "G", "V" });
		dataSet.add(new String[] { "G", "V", "A" });
		dataSet.add(new String[] { "G", "A", "V" });
		dataSet.add(new String[] { "G", "A", "V" });
		dataSet.add(new String[] { "R", "A", "B" });
		dataSet.add(new String[] { "A", "C", "B" });
		dataSet.add(new String[] { "A", "C", "D", "E", "B" });

		ArrayList<Rule> rules = Apriori.getMostFrequent(dataSet);

		System.out.println("----------------final rule-----------");
		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}
	}

}
