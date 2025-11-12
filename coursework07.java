class coursework07{
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//Order status 
	public static final int PREPARING=0;  
	public static final int DELIVERED=1; 
	public static final int CANCEL=2; 
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getOrderStatus(int status) {
    switch(status) {
        case 0: return "PREPARING";
        case 1: return "DELIVERED";
        case 2: return "CANCEL";
        default: return "UNKNOWN";
    }
}

public static void homepage(){
	
}

public static void main(String args[]){
		homepage();
		
}

































}
