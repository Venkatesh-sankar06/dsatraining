class OddEvenCounter {

    public int evenCounter(int[] arr) {
        int even = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                even++;
            }
        }
        return even;
    }

    public int oddCounter(int[] arr) {
        int odd = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                odd++;
            }
        }
        return odd;
    }
    
    public void printevenvalues(int[] arr) {
        System.out.print("Even Elements: ");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
     public void printoddvalues(int[] arr) {
        System.out.print("Even Elements: ");

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
     }

    public void printArray(int[] arr) {
        System.out.println("Array Elements:");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {21, 34, 57, 88, 90, 45, 33, 24, 12};

        OddEvenCounter counter = new OddEvenCounter();

        counter.printArray(numbers);
        counter.printoddvalues(numbers);
        counter.printevenvalues(numbers);

        System.out.println("Odd values : " + counter.oddCounter(numbers));
        System.out.println("Even values : " + counter.evenCounter(numbers));
    }
}