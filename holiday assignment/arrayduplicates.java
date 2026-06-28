class sortedarrayduplicates{
    public int removeduplicates(int[] arr){
        if (arr.length==0){
            return 0;
        }
        int ptr=1;

        for(int i=1;i<arr.length;i++){
            if(arr[i]!=arr[i-1]){
                arr[ptr]=arr[i];
                ptr++;
            }
        }
        return ptr;
    }
    public void uniqueprintelements(int[] arr , int size){
        for(int i=0;i<size;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        sortedarrayduplicates rem = new sortedarrayduplicates();
        int[] arr={1,1,2,2,3,4,5};
        
        System.out.println("original array values :");
        for(int num:arr){
            System.out.print(num+" ");
        }
        System.out.println();

        int newSize = rem.removeduplicates(arr);
        System.out.println("unique array values : ");
        rem.uniqueprintelements(arr,newSize);
        
        System.out.println("new logical size : "+ newSize);    
    }
}