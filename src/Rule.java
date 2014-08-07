import java.util.ArrayList;

public class Rule {
	ArrayList<String> ruleValue;
	int count;

	public Rule(ArrayList<String> ruleValue, int count) {
		super();
		this.ruleValue = ruleValue;
		this.count = count;
	}

	public boolean checkExisted(String item) {
		for (int i = 0; i < ruleValue.size(); i++) {
			if (ruleValue.get(i).equals(item))
				return true;
		}

		return false;
	}

	public void joinRule(Rule rule2) {
		ArrayList<String> rule2Value = rule2.ruleValue;
		ArrayList<String> removeItem = new ArrayList<>();

		for (int i = 0; i < rule2Value.size(); i++) {
			if (checkExisted(rule2Value.get(i))) {
				removeItem.add(rule2Value.get(i));
			}
		}

		rule2Value.removeAll(removeItem);
		ruleValue.addAll(rule2Value);
	}

	public void increaseCount() {
		count++;
	}

	public Rule clone() {
		return new Rule(ruleValue, count);
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < ruleValue.size(); i++) {
			if (i == ruleValue.size() - 1)
				result += ruleValue.get(i);
			else
				result += ruleValue.get(i) + ";";
		}

		result += "---Count = " + count;
		return result;
	}

	public boolean isStandInDataSet(String[] candidates) {
		int count = 0;
		for (int i = 0; i < candidates.length; i++) {
			for (String rule : ruleValue) {
				if (rule.equals(candidates[i]))
					count++;
			}
		}

		if (count == ruleValue.size())
			return true;

		return false;
	}

	public static boolean isDuplicate(Rule rule, ArrayList<Rule> rules) {
		for (Rule _rule : rules) {
			int count = 0;
			for (int i = 0; i < _rule.ruleValue.size(); i++) {
				for (int j = 0; j < rule.ruleValue.size(); j++) {
					if (_rule.ruleValue.get(i).equals(rule.ruleValue.get(j)))
						count++;
				}
			}

			if (count == rule.ruleValue.size()
					&& _rule.ruleValue.size() == rule.ruleValue.size())
				return true;
		}

		return false;
	}

	public static Rule joinRule(Rule rule1, Rule rule2) {
		ArrayList<String> rule2Value = rule2.ruleValue;
		ArrayList<String> finalRuleValue = new ArrayList<>();

		for (int i = 0; i < rule2Value.size(); i++) {
			if (!rule1.checkExisted(rule2Value.get(i))) {
				finalRuleValue.add(rule2Value.get(i));
			}
		}

		// rule2Value.removeAll(removeItem);
		finalRuleValue.addAll(rule1.ruleValue);
		// finalRuleValue.addAll(rule2Value);

		// create and reset the count
		return new Rule(finalRuleValue, 0);
	}
}
