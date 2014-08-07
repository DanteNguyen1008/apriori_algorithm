import java.util.ArrayList;
import java.util.Iterator;

public class Apriori {
	private static final float MINIMUM_SUPPORT = 0.2f;

	@SuppressWarnings("unchecked")
	public static ArrayList<Rule> getMostFrequent(ArrayList<String[]> data) {
		int minimumSupportCount = (int) Math.floor(data.size()
				* MINIMUM_SUPPORT);
		ArrayList<Rule> rules = new ArrayList<>();

		/* step 1 */
		for (String[] candidates : data) {
			for (int i = 0; i < candidates.length; i++) {
				if (rules.size() > 0) {
					boolean isCount = false;
					for (int j = 0; j < rules.size(); j++) {
						if (rules.get(j).checkExisted(candidates[i])) {
							rules.get(j).increaseCount();
							isCount = true;
						}
					}

					if (!isCount) {
						ArrayList<String> ruleValue = new ArrayList<>();
						ruleValue.add(candidates[i]);
						rules.add(new Rule(ruleValue, 1));
					}
				} else {
					ArrayList<String> ruleValue = new ArrayList<>();
					ruleValue.add(candidates[i]);
					rules.add(new Rule(ruleValue, 1));
				}
			}
		}

		System.out.println("---------Rule after step 1---------");
		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}
		/* apply minimum support */
		applyMinimumSupport(minimumSupportCount, rules);

		System.out
				.println("-------Rule after step 1 applied minimum support---------");
		for (Rule rule : rules) {
			System.out.println(rule.toString());
		}

		/* step 2 - group */
		ArrayList<Rule> finalRules = new ArrayList<>();
		ArrayList<Rule> returnRules = null;
		int count = 1;
		loop: while (true) {
			count++;
			if (finalRules.size() > 0)
				rules = (ArrayList<Rule>) finalRules.clone();
			finalRules = new ArrayList<>();

			for (int i = 0; i < rules.size(); i++) {
				for (int j = 0; j < rules.size(); j++) {
					if (i != j) {
						/* check duplicate ex : AB and BA */
						Rule finalRule = Rule.joinRule(rules.get(i),
								rules.get(j));
						if (!Rule.isDuplicate(finalRule, finalRules))
							finalRules.add(finalRule);
					}
				}
			}

			for (String[] candidates : data) {
				for (Rule rule : finalRules) {
					if (rule.isStandInDataSet(candidates))
						rule.increaseCount();
				}
			}
			
			System.out.println("---------Rule after step " + count
					+ "-------------");
			for (Rule rule : finalRules) {
				System.out.println(rule.toString());
			}

			applyMinimumSupport(minimumSupportCount, finalRules);

			if (finalRules.size() <= 0)
				break loop;

			returnRules = (ArrayList<Rule>) finalRules.clone();

			System.out.println("---------Rule after step " + count
					+ " applied minimum support---------");
			for (Rule rule : returnRules) {
				System.out.println(rule.toString());
			}
		}

		return returnRules;
	}

	private static ArrayList<Rule> applyMinimumSupport(int minimumSupportCount,
			ArrayList<Rule> rules) {
		ArrayList<Rule> removeRule = new ArrayList<>();
		for (Rule rule : rules) {
			if (rule.count < minimumSupportCount)
				removeRule.add(rule);
		}

		rules.removeAll(removeRule);
		return rules;
	}
}
