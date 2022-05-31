package main.com.ted.dissertationproject.music.princeton;

/**
 * RingBuffer class which handles the guitar notes
 * Code taken and slightly changed from: https://github.com/Loquats/Guitar/blob/master/src/GuitarString.java
 */
public class RingBuffer {
	
	private double[] arr;
	private int first, last, capacity;

	/**
	 * Constructor for Ring Buffer class 
	 * @param capacity - Full size of the ring buffer 
	 */
	public RingBuffer(int capacity) {
		this.capacity=capacity;
		arr=new double[capacity];
		first=0;
		last=0;
	}

	/**
	 * Gets the size of the RingBuffer
	 * @return The total size of the Ring Buffer
	 */
	public int size() {
		if(last>=first) {
			return last-first;
		}
		return (last+capacity)-first;
	}

	/**
	 * Checks if the ring buffer is empty
	 * @return True or False depending if the ring buffer is empty
	 */
	public boolean isEmpty() {
		if(last==first) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the ring buffer is full
	 * @return True or False depending if the ring buffer is full
	 */
	public boolean isFull() {
		if(first-1==last || (first+capacity)-1==last) {
			return true;
		}
		return false;
	}

	/**
	 * Add an item to the end of the ring buffer
	 * @param x - Value to be added to the ring buffer
	 */
	public void enqueue(double x){
		if(isFull()){
			
		} else if(last==capacity) {
			last=1;
			arr[0]=x;
		} else {
			arr[last]=x;
			last++;
		}
	}

	/**
	 * Deletes and returns the last item from the ring buffer 
	 * @return Value to be removed from the ring buffer
	 */
	public double dequeue() {
		double ret=arr[first];
		if(first==capacity-1) {
			first=0;
		} else {
			first++;
		}
		return ret;
	}

	/**
	 * Returns the item from the front of the ring buffer
	 * @return Value at the front of the ring buffer
	 */
	public double peek(int loc) {
		if(first+loc>=capacity) {
			return arr[first+loc-capacity];
		}
		return arr[first+loc];
	}
}
