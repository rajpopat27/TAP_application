package com.biomatiques.model;
import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Iris {
		public Iris() {
			
		}

	    @JsonProperty("employeeId")
    	private long id;
	    
	    
	    @JsonProperty("leftIris")	
        public byte[] leftIris;
		
	    
	    @JsonProperty("rightIris")
        public byte[] rightIris;
	  		    
        public byte[] getLeftIris() {
			return leftIris;
		}

		public void setLeftIris(byte[] leftIris) {
			this.leftIris = leftIris;
		}

		public byte[] getRightIris() {
			return rightIris;
		}

		public void setRightIris(byte[] rightIris) {
			this.rightIris = rightIris;
		}
		
		public void setId(long id) {
			this.id = id;
		}

		public long getId() {
	    		return id;
    	}
	}
