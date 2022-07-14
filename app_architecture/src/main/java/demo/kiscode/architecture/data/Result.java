package demo.kiscode.architecture.data;

public class Result<T> {

    public final static class SuccessResult<T> extends Result<T> {
        private T data;

        public SuccessResult(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public final static class ErrorResult<T> extends Result<T> {
        private Exception exception;

        public ErrorResult(Exception exception) {
            this.exception = exception;
        }

        public Exception getException() {
            return exception;
        }

    }
} 