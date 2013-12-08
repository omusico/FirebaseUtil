package us.jsy.firebase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GeoFence {
	String label;
	String address;
	float radius;
	Location location;
	Pointer group;
	boolean markDeleted;
	String updatedAt;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Firebase("https://abeona-dev.firebaseio.com/Group/2lgAHcCvco/GeoFence/T7HCXys2nA").addValueEventListener(new ValueEventListener() {
			
			@Override
			public void onDataChange(DataSnapshot data) {
				final GeoFence value = data.getValue(GeoFence.class);
				System.out.println(data.getName() + value);
				
				data.getRef().getParent().child("00").setValue(value);
			}
			
			@Override
			public void onCancelled() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public String toString() {
		return "GeoFence [label=" + label + ", address=" + address
				+ ", radius=" + radius + ", location=" + location + ", group="
				+ group + ", markDeleted=" + markDeleted + ", updatedAt="
				+ updatedAt + "]";
	}

	public String getLabel() {
		return label;
	}

	public String getAddress() {
		return address;
	}

	public float getRadius() {
		return radius;
	}

	public Location getLocation() {
		return location;
	}

	public Pointer getGroup() {
		return group;
	}

	public boolean isMarkDeleted() {
		return markDeleted;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		System.err.println("setUpdatedAt");
		this.updatedAt = updatedAt;
	}

}

@JsonIgnoreProperties(ignoreUnknown=true)
class Pointer {
	String className;
	String objectId;
	public String getClassName() {
		return className;
	}
	public String getObjectId() {
		return objectId;
	}
	@Override
	public String toString() {
		return "Pointer [className=" + className + ", objectId=" + objectId
				+ "]";
	}
}
