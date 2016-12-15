
public class StopWatch {
	private long start;
	private long finish;

	//============================= Constructor ==========================================
	/**
	 * Constructor
	 */
	public StopWatch() {
		
	}

	//============================== Methods =======================================
	
	/**
	 * Setters and Getters
	 */
	public void start() {
		this.start = System.nanoTime();
	}


	public void finish() {
		this.finish = System.nanoTime();
	}

	
	public long getTotalRumTime(){
		return this.finish-this.start;
	}
	
}
