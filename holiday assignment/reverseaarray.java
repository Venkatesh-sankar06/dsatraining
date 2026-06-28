class reverseaarraydisplay{
    public void printoriginalarray(int[] arr){
         System.out.println("original array values are: ");

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

     public void printreversearray(int[] arr){
         System.out.println("Reverse array values are: ");

        for(int i=arr.length-1;i>=0;i--){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }

     public static void main(String[] args){

        int[] array={12,13,14,15,16,90,89};
        reverseaarraydisplay reverse = new  reverseaarraydisplay();
        reverse.printoriginalarray(array);
        reverse.printreversearray(array);

    }

}