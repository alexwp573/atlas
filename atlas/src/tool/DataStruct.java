package atlas.tool;

public class DataStruct{

	// --------------
	// ERROR HANDLERS
	public class OutOfBoundsException extends Exception{

		private int indexCalled = 0;
		private int maxIndex = 0;

		public OutOfBoundsException(int indexCalled, int maxIndex){this.indexCalled = indexCalled; this.maxIndex = maxIndex;}

		public String getString(){return "Index called: " + this.indexCalled + ", but maximum index callable is: " + this.maxIndex;}

	}

	// ------------
	// DATA HOLDERS
	public class Pair<T, U>{

		// ---------------------------
		// PRIVATE OPERATIONS & FIELDS
		private T key = null; public T getKey(){return this.key;}
		private U val = null; public U getVal(){return this.val;}

		// -----------------
		// PUBLIC OPERATIONS

			// ------------
			// CONSTRUCTORS
			// - implementation:
			// 		DataStruct.Table<T> VAR_NAME = new DataStruct().new Table<>();
			public Pair(T key, U val){this.key = key; this.val = val;}
	}
	public class Table<T>{

		// ---------------------------
		// PRIVATE OPERATIONS & FIELDS
		private int size = 0; // count of indexes from 0 to index of last element
		private int count = 0; // count of all elements
			private void recalculate(){this.size(); this.count();} // calculates size and count

		private T[] data = (T[]) new Object[1];
			private void resize(){ // doubles capacity for elements
				T[] temp = (T[]) new Object[this.data.length * 2];
				for(int i = 0; i < this.data.length; i++) temp[i] = this.data[i];
				for(int i = this.size + 1; i < temp.length; i++) temp[i] = null;
				this.data = temp;
			}
			private void checkCapacity(){ // checks capacity of elements for one extra, resizes if needed
				if(this.size >= this.data.length) this.resize();
			}
			private boolean moveUp(int startIndex, int endIndex){ // moves all elements for next index starting from given index (int startIndex) ending at (int endIndex)
				this.checkCapacity();
				if(
					startIndex < endIndex &&
					startIndex >= 0 &&
					endIndex <= this.size &&
					this.data[endIndex] == null){
						for(int i = endIndex; i > startIndex; i--) this.data[i] = this.data[i-1];
						this.data[startIndex] = null;
						this.recalculate();
						return true;
				}
				return false;
			}
			private boolean moveDown(int startIndex, int endIndex){ // moves all elements for prev index starting from given index (int startIndex) ending at (int endIndex)
				if(
					startIndex < endIndex &&
					startIndex >= 0 &&
					endIndex <= this.size &&
					this.data[startIndex] == null){
						for(int i = startIndex; i < endIndex; i++) this.data[i] = this.data[i+1];
						this.data[endIndex] = null;
						this.recalculate();
						return true;
				}return false;
			}

		// -----------------
		// PUBLIC OPERATIONS
	
			// ------------
			// CONSTRUCTORS
			// - implementation:
			// 		DataStruct.Table<T> VAR_NAME = new DataStruct().new Table<>();
			public Table(){ // creates object with-out any element
				this.data[0] = null;
			}
			public Table(T element){ // creates object with one element (T element)
				this.data[0] = element;
				this.recalculate();
			}

			// ------
			// ADDERS
			public void insert(T element){ // inserts element (T element) at index 0
				this.checkCapacity();
				this.moveUp(0, this.size);
				this.data[0] = element;
				this.recalculate();
			}
			public void add(T element){ // adds element (T element) at the end
				this.checkCapacity();
				this.data[this.size] = element;
				this.recalculate();
			}
			public void push(T element, int index){ // adds element (T element) at index (int index)
				this.checkCapacity();
				if(index < this.size) this.moveUp(index, this.size);
				this.data[index] = element;
				this.recalculate();
			}
			public boolean put(T element, int index){ // adds element (T element) at index (int index) if that index is null. Returns true when index is null and false when its not null
				this.checkCapacity();
				if(this.data[index] == null){
					this.data[index] = element;
					this.recalculate();
					return true;
				} return false;
			}
			public int fill(T element){ // finds firt apperance of null and puts element (T element) there
				this.checkCapacity();
				for(int i = 0; i < this.data.length; i++) if(this.data[i] == null){this.data[i] = element; this.recalculate(); return i;}
				return -1;
			}

			// --------
			// REMOVERS
			public T remove(){ // returns and removes first element
				T elementToReturn = this.data[0];
				this.data[0] = null;
				this.recalculate();
				return elementToReturn;
			}
			public T take(){ // returns and removes last element
				T elementToReturn = this.data[this.size-1];
				this.data[this.size-1] = null;
				this.recalculate();
				return elementToReturn;
			}
			public T pull(int index){ // returns and removes element from index (int index) and pulls all elements from bigger indexes down
				T elementToReturn = this.data[index];
				this.data[index] = null;
				this.moveDown(index, this.size-1);
				this.recalculate();
				return elementToReturn;
			}
			public T pull(){ // returns and removes element from index 0 and pulls all elements from bigger indexes down
				T elementToReturn = this.data[0];
				this.data[0] = null;
				this.moveDown(0, this.size-1);
				this.recalculate();
				return elementToReturn;
			}
			public T get(int index){ // returns and removes element from index () and puts null at index (int index)
				T elementToReturn = this.data[index];
				this.data[index] = null;
				this.recalculate();
				return elementToReturn;
			}
			public void erase(){for(int i = 0; i < this.data.length; i++) this.data[i] = null;}

			// ------------
			// MODIFICATORS
			public T replace(T element, int index){ // returns element from index (int index) and changes it for (T element)
				if(this.data[index] != null) this.count++;
				if(index >= this.size) this.size = index;
				T elementToReturn = this.data[index];
				this.data[index] = element;
				this.recalculate();
				return elementToReturn;
			}
			public void replace(int index1, int index2){ // changes elements on two indexes (int index1, int index2)
				T elementHolder = this.data[index1];
				this.data[index1] = this.data[index2];
				this.data[index2] = elementHolder;
				this.recalculate();
			}
			public int squize(){ // returns count of nulls between element 0 and last element, pulls all elements down and puts nulls on last indexes
				int nullsCount = 0;
				for(int i = 0; i < this.size; i++)if(this.data[i] == null){nullsCount++; this.moveDown(i, this.size);}
				this.recalculate();
				return nullsCount;
			}
			public void move(int index1, int index2){ // moves element from index (int index1) to index (int index2) and pushes all other elements between them up/down
				T elementHolder = this.get(index1);
				if(index1 < index2) this.moveDown(index1, index2);
				else if(index1 > index2) this.moveUp(index2, index1);
				this.data[index2] = elementHolder;
				this.recalculate();
			}
			public int trim(){ // resizes down the elements array to size --- not finished
				return 0;
			}

			// --------
			// CHECKERS
			public T check(int index){ // returns element at index (int index)
				return this.data[index];
			}
			public T checkLast(){ // returns last element
				return this.data[this.size-1];
			}
			public String toString(){ // {test on String elements} returns all elements as a string
				String stringToPrint = "Elements count: " + this.count + "\tSize: " + this.size + "\t\tCapacity: " + this.data.length + "\n";
				for(int i = 0; i < this.data.length; i++){
					stringToPrint += "Element index: " + i + "\tElement vlaue: " + this.data[i] + "\n";
				}
				return stringToPrint;
			}
			public int size(){ // calculates, saves and returns count of indexes from 0 to index of last element
				int tempSize = 0;
				for(int i = 0; i < this.data.length; i++) if(this.data[i] != null) tempSize = i+1;
				this.size = tempSize;
				return this.size;
			}
			public int count(){ // calculates, saves and returns count of all elements
				int tempCount = 0;
				for(int i = 0; i < this.data.length; i++) if(this.data[i] != null) tempCount++;
				this.count = tempCount;
				return this.count;
			}
	}
}