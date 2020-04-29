package etf.nwt.systemevents;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.grpc.Server;
import io.grpc.ServerBuilder;

// @SpringBootApplication
public class GrpcServer {
	public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8089)
          .addService(new EventsServiceImpl()).build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }
}
