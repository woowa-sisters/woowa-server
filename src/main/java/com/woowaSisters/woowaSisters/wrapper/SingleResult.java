package com.woowaSisters.woowaSisters.wrapper;

public class SingleResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SingleResult(T data) {
        this.success = true;
        this.data = data;
        this.error = null;
    }

    // Getter와 Setter 추가
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
