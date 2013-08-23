package us.jsy.firebase;

import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

public class OnlineCount {
	public static void start(final String url, String id) {
		final Firebase firebase = new Firebase(url).child(id);
		Firebase connectedRef = firebase.getRoot().child(".info/connected");
		
		connectedRef.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
		        boolean connected = snapshot.getValue(Boolean.class);
		        if (connected) {
					System.err.println("connected");
					firebase.onDisconnect().removeValue();

					firebase.setValue(true);
		        } else {
					System.err.println("disconnected");
		        }
		    }

		    @Override
		    public void onCancelled() {
				System.err.println("cancelled");
		    }
		});
		
		firebase.getParent().addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot arg0) {
				System.out.println(arg0.getValue(Map.class).size() + " online");
			}
			
			@Override
			public void onCancelled() {
				System.err.println("cancelled");
			}
		});
	}
	
	public static void main(String[] args) {
		start("https://online.firebaseio-demo.com/public/online", "" + System.currentTimeMillis());
	}
}
