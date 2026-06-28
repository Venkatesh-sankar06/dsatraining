class linearsearch{
    public int searchelement(int[] arr,int target){
        for(int i=0;i<arr.length;i++){
            if(arr[i] == target){
                return i;
            }
        }
        return -1;
    }
    public void printarrays(int[] arr){
        System.out.println("array values are : ");

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();

    }
    public static void main(String[] args){

        int[] arr={12,56,78,90,45,33,3,45};
        linearsearch search = new linearsearch();
        search.printarrays(arr);
         
        int target1 = 90;
        int result1 = search.searchelement(arr,target1);
        if(result1 !=-1){
            System.out.println(target1+" found at index "+result1);
        }else{
            System.out.println(target1+"not found");
        }
        int target2 = 100;
        int result2 = search.searchelement(arr,target2);
        if(result2 !=-1){
            System.out.println(target2+" found at index "+result2);
        }else{
            System.out.println(target2+" not found");
        }
    }
}