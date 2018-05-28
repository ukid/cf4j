package cf4j.algorithms;

import cf4j.data.DataModel;
import cf4j.data.TestUser;
import cf4j.process.TestUsersPartible;

/**
 * <p>Abstract class to handle predictions for test item rated by test users. If
 * you want to compute predictions to be used by quality measures, you must
 * extend this class and redefine predict method.</p>
 * 
 * <p> The results are stored in double array on the hashmap of each test user with 
 * the key "predictions". This array overlaps with the test items' array of the test 
 * users. For example, the prediction retrieved with the method 
 * testUser.getPredictions()[i] is the prediction of the item testUser.getTestItems()[i].</p>
 * 
 * @author Fernando Ortega
 */
public abstract class TestPredictions implements TestUsersPartible {

	@Override
	public void beforeRun() { }

	@Override
	public void run (int testUserIndex) {

		TestUser testUser = DataModel.gi().getTestUserByIndex(testUserIndex);
				
		int numRatings = testUser.getNumberOfTestRatings();
		double [] predictions = new double [numRatings];
		
		for (int i = 0; i < numRatings; i++) {
			int itemCode = testUser.getTestItemAt(i);
			double prediction = predict(testUser, itemCode);
			predictions[i] = prediction;
		}

		testUser.setPredictions(predictions);
	}

	@Override
	public void afterRun() { }
	
	/**
	 * Compute the prediction of the rating to an item.
	 * @param testUser User to get the prediction.
	 * @param itemCode Item to be predicted.
	 * @return Prediction value or Double.NaN if it can not be computed.
	 */
	public abstract double predict (TestUser testUser, int itemCode);
}