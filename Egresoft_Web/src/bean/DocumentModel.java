package bean;

import java.io.Serializable;

public class DocumentModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int srNo;
	String documentName;
	
	boolean removable = Boolean.TRUE; // to mention whether the document can be
										// removed or not
	boolean uploaded = Boolean.FALSE; // flag to mention file uploaded status
	private String documentUploadedPath;// to capture uploaded path

	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	public String getDocumentUploadedPath() {
		return documentUploadedPath;
	}

	public void setDocumentUploadedPath(String documentUploadedPath) {
		this.documentUploadedPath = documentUploadedPath;
	}

	public boolean isUploaded() {
		return uploaded;
	}

	public void setUploaded(boolean uploaded) {
		this.uploaded = uploaded;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

}
