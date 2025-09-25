import org.javase.from21To25.ServerResponse;

void main() {
    try {

        String name = IO.readln("Enter your name: ");
        IO.println("Nice to meet you " + name);
        analyze(new ServerResponse.ServerError(500, "Internal Server Error"));
    } catch (Exception _) {
    }
}

/// ***This method takes an instance [org.javase.from21To25.ServerResponse] type and prints response***
void analyze(ServerResponse serverResponse) {
    switch (serverResponse) {
        case ServerResponse.Response _ -> IO.println("Successful");
        case ServerResponse.NotFount _ -> IO.println("Not Found");
        case ServerResponse.ServerError(_, String message) -> IO.println(message);
    }
}