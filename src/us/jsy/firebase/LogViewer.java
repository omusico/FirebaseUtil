package us.jsy.firebase;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.security.token.TokenGenerator;
import com.firebase.security.token.TokenOptions;
import com.firebase.simplelogin.SimpleLogin;
import com.firebase.simplelogin.SimpleLoginAuthenticatedHandler;
import com.firebase.simplelogin.User;

public class LogViewer {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		JSONObject arbitraryPayload = new JSONObject();
		try {
		    arbitraryPayload.put("some", "arbitrary");
		    arbitraryPayload.put("data", "here");
		} catch (JSONException e) {
		    e.printStackTrace();
		}   

		TokenGenerator tokenGenerator = new TokenGenerator("8pguWAJt7bVjuY9mvKpNnd7LoLygjv3diNmbce9w");
		TokenOptions tokenOptions = new TokenOptions();
		tokenOptions.setAdmin(true);
		String token = tokenGenerator.createToken(arbitraryPayload, tokenOptions);

		System.out.println(token);
		
		String url = "https://voved.firebaseio.com/write/install";
		Firebase ref = new Firebase(url);

		ref.auth(token, new Firebase.AuthListener() {

		    @Override
		    public void onAuthError(FirebaseError error) {
		        System.err.println("Login Failed! " + error.getMessage());
		    }

		    @Override
		    public void onAuthSuccess(Object authData) {
		        System.out.println("Login Succeeded!" + authData);
		    }

		    @Override
		    public void onAuthRevoked(FirebaseError error) {
		        System.err.println("Authenticcation status was cancelled! " + error.getMessage());
		    }

		});
		
		SimpleLogin authClient = new SimpleLogin(ref);
//		authClient.cr
//		authClient.checkAuthStatus(new SimpleLoginAuthenticatedHandler() {
//			@Override
//			public void authenticated(Error error, User user) {
//				if (error != null) {
//					// Oh no! There was an error performing the check
//				} else if (user == null) {
//					// No user is logged in
//				} else {
//					// There is a logged in user
//				}
//			}
//
//		});

		authClient.createUser("v@jsy.us", "ok", new SimpleLoginAuthenticatedHandler() {
			public void authenticated(com.firebase.simplelogin.enums.Error error, User user) {
				if (error != null) {
					// There was an error creating this account
				} else {
					// We are now logged in
				}
			}
		});

		ref.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot snapshot) {
				final Object value = snapshot.getValue();
				System.out.println("Fred's first name is "
						+ value);
				snapshot.getRef().getRoot().child("read/home").setValue(((Map<?, ?>)value).size() + " online");
			}

			@Override
			public void onCancelled() {
				System.err.println("Listener was cancelled");
			}
		});
		
		final Firebase clock = ref.getRoot().child("read/clock");
		
		while (true) {
			clock.setValue(new Date().toString());
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
