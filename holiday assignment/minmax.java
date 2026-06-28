class minarray {

    public int findmin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public int findmax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public void printarrays(int[] arr) {
        System.out.println("arrays elements");

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] numbers = {45, 34, 56, 89, 3, 44, 23, 45};

        minarray obj = new minarray();

        obj.printarrays(numbers);
        System.out.println("min values : " + obj.findmin(numbers));
        System.out.println("max values : " + obj.findmax(numbers));
    }
}