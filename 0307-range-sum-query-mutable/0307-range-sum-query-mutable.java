
//Approach -2

class NumArray {
    private int[] segmentTree; // Array to store segment tree
    private int arrayLength; // Length of the input array

    // Constructor to initialize the segment tree with the given array
    public NumArray(int[] nums) {
        this.arrayLength = nums.length; // Set the length of the input array
        if (arrayLength > 0) { // If array is not empty
            int height = (int) (Math.ceil(Math.log(arrayLength) / Math.log(2))); // Calculate height of the segment tree
            int maxSize = 2 * (int) Math.pow(2, height) - 1; // Maximum size of the segment tree
            segmentTree = new int[maxSize]; // Initialize the segment tree array
            buildSegmentTree(nums, 0, arrayLength - 1, 0); // Build the segment tree
        }
    }

    // Utility function to get the middle index of a range
    private int getMiddle(int start, int end) {
        return start + (end - start) / 2;
    }

    // Recursive function to build the segment tree
    private void buildSegmentTree(int[] arr, int segmentStart, int segmentEnd, int currentIndex) {
        // If the segment start is equal to the segment end, it's a leaf node
        if (segmentStart == segmentEnd) {
            segmentTree[currentIndex] = arr[segmentStart]; // Set the leaf node value
            return;
        }
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        buildSegmentTree(arr, segmentStart, mid, currentIndex * 2 + 1); // Recursively build the left subtree
        buildSegmentTree(arr, mid + 1, segmentEnd, currentIndex * 2 + 2); // Recursively build the right subtree
        // Set the current node value as the sum of left and right children
        segmentTree[currentIndex] = segmentTree[currentIndex * 2 + 1] + segmentTree[currentIndex * 2 + 2];
    }

    // Function to update an element in the segment tree
    public void update(int index, int newValue) {
        if (arrayLength == 0) return; // If the array is empty, do nothing
        int difference = newValue - getElement(0, arrayLength - 1, index, 0); // Calculate the difference
        updateSegmentTree(0, arrayLength - 1, index, difference, 0); // Update the segment tree
    }

    // Utility function to get the value of an element in the segment tree
    private int getElement(int segmentStart, int segmentEnd, int queryIndex, int currentIndex) {
        // If the segment start is equal to the segment end, it's a leaf node
        if (segmentStart == segmentEnd) {
            return segmentTree[currentIndex]; // Return the leaf node value
        }
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        // Recursively get the element value from the left or right subtree
        if (queryIndex <= mid) {
            return getElement(segmentStart, mid, queryIndex, 2 * currentIndex + 1);
        } else {
            return getElement(mid + 1, segmentEnd, queryIndex, 2 * currentIndex + 2);
        }
    }

    // Recursive function to update the segment tree
    private void updateSegmentTree(int segmentStart, int segmentEnd, int index, int difference, int currentIndex) {
        // If the index is outside the segment range, return
        if (index < segmentStart || index > segmentEnd) return;
        segmentTree[currentIndex] += difference; // Update the current node value
        // If the segment start is not equal to the segment end, update the children
        if (segmentStart != segmentEnd) {
            int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
            updateSegmentTree(segmentStart, mid, index, difference, 2 * currentIndex + 1); // Update the left subtree
            updateSegmentTree(mid + 1, segmentEnd, index, difference, 2 * currentIndex + 2); // Update the right subtree
        }
    }

    // Function to get the sum of elements in the given range
    public int sumRange(int left, int right) {
        if (arrayLength == 0) return 0; // If the array is empty, return 0
        return getSum(0, arrayLength - 1, left, right, 0); // Get the sum from the segment tree
    }

    // Recursive function to get the sum of elements in the given range
    private int getSum(int segmentStart, int segmentEnd, int queryStart, int queryEnd, int currentIndex) {
        // If the query range is within the segment range, return the current node value
        if (queryStart <= segmentStart && queryEnd >= segmentEnd) {
            return segmentTree[currentIndex];
        }
        // If the segment range is outside the query range, return 0
        if (segmentEnd < queryStart || segmentStart > queryEnd) {
            return 0;
        }
        int mid = getMiddle(segmentStart, segmentEnd); // Get the middle index
        // Recursively get the sum from the left and right subtrees
        return getSum(segmentStart, mid, queryStart, queryEnd, 2 * currentIndex + 1) +
               getSum(mid + 1, segmentEnd, queryStart, queryEnd, 2 * currentIndex + 2);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
