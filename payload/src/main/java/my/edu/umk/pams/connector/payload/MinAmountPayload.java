package my.edu.umk.pams.connector.payload;

import java.math.BigDecimal;

public class MinAmountPayload {

	private StudentPayload studentPayload;
	private BigDecimal minimalAmount;
	
	
	public StudentPayload getStudentPayload() {
		return studentPayload;
	}
	public void setStudentPayload(StudentPayload studentPayload) {
		this.studentPayload = studentPayload;
	}
	public BigDecimal getMinimalAmount() {
		return minimalAmount;
	}
	public void setMinimalAmount(BigDecimal minimalAmount) {
		this.minimalAmount = minimalAmount;
	}
	
	
	
}
