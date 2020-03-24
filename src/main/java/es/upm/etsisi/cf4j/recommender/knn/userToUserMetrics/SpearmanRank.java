package es.upm.etsisi.cf4j.recommender.knn.userToUserMetrics;

import es.upm.etsisi.cf4j.data.DataModel;
import es.upm.etsisi.cf4j.data.User;

/**
 * Implements traditional Sepearman Rank as CF similarity metric.
 * 
 * @author Fernando Ortega
 */
public class SpearmanRank extends UserToUserMetric {

	@Override
	public double similarity(User user, User otherUser) {

		int i = 0, j = 0, common = 0; 
		double num = 0d;
		
		while (i < user.getNumberOfRatings() && j < otherUser.getNumberOfRatings()) {
			if (user.getItemAt(i) < otherUser.getItemAt(j)) {
				i++;
			} else if (user.getItemAt(i) > otherUser.getItemAt(j)) {
				j++;
			} else {
				double diff = user.getRatingAt(i) - otherUser.getRatingAt(j);
				num += diff * diff;
				common++;
				i++; 
				j++;
			}	
		}

		// If there is not items in common, similarity does not exists
		if (common == 0) return Double.NEGATIVE_INFINITY;

		// Return similarity
		return 1d - ((6d * num) / (common * ((common * common) - 1d)));
	}
}
