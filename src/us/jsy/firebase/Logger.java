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

public class Logger {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		final String secret = "d6S5uPk0LbqtruJif0TUuS6AgxgOFO24CMTKaZjH";
		TokenGenerator tokenGenerator = new TokenGenerator(secret);
		TokenOptions tokenOptions = new TokenOptions();
		tokenOptions.setAdmin(true);
		tokenOptions.setExpires(new Date(System.currentTimeMillis() + 1000*60*60*24*7));
		String token = tokenGenerator.createToken(null, tokenOptions);

		System.out.println(token);
		
		String url = "https://abeona.firebaseio.com/chat";
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

//		authClient.createUser("v@jsy.us", "ok", new SimpleLoginAuthenticatedHandler() {
//			public void authenticated(com.firebase.simplelogin.enums.Error error, User user) {
//				if (error != null) {
//					// There was an error creating this account
//				} else {
//					// We are now logged in
//				}
//			}
//		});
		
		final Firebase clock = ref.getRoot().child("read/clock");
		
		while (true) {
			ref.push().setValue(new Message("test", "" + new Date()));
			TimeUnit.SECONDS.sleep(1);
		}

	}

}
