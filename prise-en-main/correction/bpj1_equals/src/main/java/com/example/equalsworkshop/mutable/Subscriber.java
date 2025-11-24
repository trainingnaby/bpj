package com.example.equalsworkshop.mutable;

import java.util.Objects;

public class Subscriber {

	 private String email; 
	 
	 public Subscriber(String email) {
		 this.email = email;
	 }
	 
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 
	 @Override
	 public boolean equals(Object obj) {
		 if (obj == null) {
			 return false;
		 }
		 if (this == obj) {
			 return true;
		 }
		 if (!(obj instanceof Subscriber)) {
			 return false;
		 }
		 Subscriber other = (Subscriber) obj;
		 return java.util.Objects.equals(this.email, other.email);
	 }
	 
	 @Override
	 public int hashCode() {
		 return Objects.hash(email);
	 }
}
