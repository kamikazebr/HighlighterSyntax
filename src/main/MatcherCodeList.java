package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MatcherCodeList extends ArrayList<MatcherCode> implements Comparator<MatcherCode> {

	private static final long serialVersionUID = -789616343381593425L;

	public MatcherCodeList() {
	}

	public void sort() {
		Collections.sort(this, this);
	}

	@Override
	public int compare(MatcherCode o1, MatcherCode o2) {
		int start1 = o1.getStart();
		int start2 = o2.getStart();

		if (start1 > start2) {
			return 1;
		} else if (start1 < start2) {
			return -1;
		}
		return 0;
	}

	public void removeIntersection() {
		MatcherCodeList codeListTemp = new MatcherCodeList();

		// Iterator<MatcherCode> iterator2 = iterator();
		// while (iterator2.hasNext()) {
		// MatcherCode matcher = iterator2.next();
		// int start1 = matcher.getStart();
		// int end1 = matcher.getEnd();
		// Iterator<MatcherCode> iterator3 = iterator();
		// while (iterator3.hasNext()) {
		// MatcherCode matcher2 = iterator3.next();
		// if (matcher == matcher2) {
		// continue;
		// }
		// int start2 = matcher2.getStart();
		// int end2 = matcher2.getEnd();
		// boolean contains = codeListTemp.contains(matcher2);
		// boolean b = start2 >= start1;
		// boolean c = end2 <= end1;
		// boolean c2 = b && c;
		// if (c2 && !contains) {
		// codeListTemp.add(matcher2);
		// }
		// }
		// }
		//
		// Iterator<MatcherCode> iterator4 = codeListTemp.iterator();
		// while (iterator4.hasNext()) {
		// remove(iterator4.next());
		// }

		for (MatcherCode matcher : this) {
			int start1 = matcher.getStart();
			int end1 = matcher.getEnd();
			for (MatcherCode matcher2 : this) {
				if (matcher == matcher2) {
					continue;
				}
				int start2 = matcher2.getStart();
				int end2 = matcher2.getEnd();
				boolean contains = codeListTemp.contains(matcher2);
				boolean b = start2 >= start1;
				boolean c = end2 <= end1;
				boolean c2 = b && c;
				if (c2 && !contains) {
					codeListTemp.add(matcher2);
				}
			}
		}
		
		for (MatcherCode matcherCode : codeListTemp) {
			remove(matcherCode);
		}
	}
}
