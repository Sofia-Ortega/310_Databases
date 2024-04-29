package org.example;

import com.hp.gagawa.java.elements.*;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Map;
import java.util.regex.*;


public class WebServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8500), 0);
        HttpContext root = server.createContext("/");
        root.setHandler(WebServer::handleRequest);

        HttpContext context = server.createContext("/users");
        context.setHandler(WebServer::handleRequestUser);

        HttpContext product = server.createContext("/products");
        product.setHandler(WebServer::handleRequestOneProduct);

        HttpContext allproducts = server.createContext("/products/all");
        allproducts.setHandler(WebServer::handleRequestAllProducts);

        HttpContext productSearch = server.createContext("/products/search");
        productSearch.setHandler(WebServer::handleRequestProductSearch);


        server.start();
    }

    private static void handleRequestProductSearch(HttpExchange exchange) throws IOException {
        String uri =  exchange.getRequestURI().toString();
        System.out.println("URI: " + uri);
        Pattern pattern = Pattern.compile("minprice=(\\d+)&maxprice=(\\d+)");
        Matcher matcher = pattern.matcher(uri);

        double minPrice = 0;
        double maxPrice = 0;

        // Find min and max prices if the pattern matches
        if (matcher.find()) {
            minPrice = Double.parseDouble(matcher.group(1));
            maxPrice = Double.parseDouble(matcher.group(2));
        } else {
            System.out.println("URL format doesn't match.");
            return;
        }

        // Output the parsed prices
        System.out.println("Min Price: " + minPrice);
        System.out.println("Max Price: " + maxPrice);


        // get data
        DataAccess dao = new DataAccess();
        List<Map<String, String>> list = dao.getProducts();

        // ------------- HTML ----------------------
        Html html = new Html();
        Head head = new Head();

        html.appendChild( head );

        Title title = new Title();
        title.appendText("Product list");
        head.appendChild( title );

        Body body = new Body();

        html.appendChild( body );

        H1 h1 = new H1();
        h1.appendText("Product list");
        body.appendChild( h1 );


        P para = new P();
        para.appendChild( new Text("The server has " + list.size() + " products in the range of $" + minPrice + " to $" + maxPrice));
        body.appendChild(para);


        Table table = new Table();
        Tr row = new Tr();
        Th header = new Th(); header.appendText("Number"); row.appendChild(header);
        header = new Th(); header.appendText("Product name"); row.appendChild(header);
        header = new Th(); header.appendText("Price"); row.appendChild(header);
        header = new Th(); header.appendText("Quantity"); row.appendChild(header);
        table.appendChild(row);

        Integer counter = 0;
        for (Map<String, String> product : list) {

            double price = Double.parseDouble(product.get("price"));
            if(price < minPrice || price > maxPrice) {
                System.out.println("skip: " + product.get("productName"));
                continue;
            }

            row = new Tr();
            Td cell = new Td(); cell.appendText(counter.toString()); row.appendChild(cell);
            cell = new Td(); cell.appendText(product.get("productName")); row.appendChild(cell);
            cell = new Td(); cell.appendText(String.valueOf(product.get("price"))); row.appendChild(cell);
            cell = new Td(); cell.appendText(String.valueOf(product.get("quantity"))); row.appendChild(cell);
            table.appendChild(row);

            counter++;
        }

        table.setBorder("1");

        html.appendChild(table);

        String response = html.write();

        System.out.println(response);


        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

    private static void handleRequest(HttpExchange exchange) throws IOException {

        Html html = new Html();
        Head head = new Head();

        html.appendChild( head );

        Title title = new Title();
        title.appendText("Online shopping web server");
        head.appendChild( title );

        Body body = new Body();

        H1 h1 = new H1();
        h1.appendText("Search Results");

        body.appendChild(h1);

        P para = new P();

        A link = new A("/products/all", "/products/all");
        link.appendText("Product list");

        para.appendChild(link);
        body.appendChild(para);
        html.appendChild( body );


        String response = html.write();
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handleRequestUser(HttpExchange exchange) throws IOException {
        String response = "Hi there! I am a simple web server USER!";
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void handleRequestAllProducts(HttpExchange exchange) throws IOException {

        DataAccess dao = new DataAccess();

        List<Map<String, String>> list = dao.getProducts();

        Html html = new Html();
        Head head = new Head();

        html.appendChild( head );

        Title title = new Title();
        title.appendText("Product list");
        head.appendChild( title );

        Body body = new Body();

        html.appendChild( body );

        H1 h1 = new H1();
        h1.appendText("Product list");
        body.appendChild( h1 );

        P para = new P();
        para.appendChild( new Text("The server time is " + LocalDateTime.now()) );
        body.appendChild(para);

        para = new P();
        para.appendChild( new Text("The server has " + list.size() + " products." ));
        body.appendChild(para);

        Table table = new Table();
        Tr row = new Tr();
        Th header = new Th(); header.appendText("ProductID"); row.appendChild(header);
        header = new Th(); header.appendText("Product name"); row.appendChild(header);
        header = new Th(); header.appendText("Price"); row.appendChild(header);
        header = new Th(); header.appendText("Quantity"); row.appendChild(header);
        table.appendChild(row);

        Integer counter = 0;
        for (Map<String, String> product : list) {
            row = new Tr();
            Td cell = new Td(); cell.appendText(counter.toString()); row.appendChild(cell);
            cell = new Td(); cell.appendText(product.get("productName")); row.appendChild(cell);
            cell = new Td(); cell.appendText(String.valueOf(product.get("price"))); row.appendChild(cell);
            cell = new Td(); cell.appendText(String.valueOf(product.get("quantity"))); row.appendChild(cell);
            table.appendChild(row);

            counter++;
        }

        table.setBorder("1");

        html.appendChild(table);
        String response = html.write();

        System.out.println(response);


        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }


    private static void handleRequestOneProduct(HttpExchange exchange) throws IOException {

        String uri =  exchange.getRequestURI().toString();

        int id = Integer.parseInt(uri.substring(uri.lastIndexOf('/')+1));

        System.out.println(id);

//        RemoteDataAdapter dao = new RemoteDataAdapter();

        /*
        DataAccess dao = new SQLiteDataAdapter();
        dao.connect("jdbc:sqlite:store.db");
        ProductModel product = dao.loadProduct(id);

        Html html = new Html();
        Head head = new Head();

        html.appendChild( head );

        Title title = new Title();
        title.appendChild( new Text("Example Page Title") );
        head.appendChild( title );

        Body body = new Body();

        html.appendChild( body );

        H1 h1 = new H1();
        h1.appendChild( new Text("Example Page Header") );
        body.appendChild( h1 );

        P para = new P();
        para.appendChild( new Text("The server time is " + LocalDateTime.now()) );
        body.appendChild(para);



        if (product != null) {

            para = new P();
            para.appendText("ProductID:" + product.productID);
            html.appendChild(para);
            para = new P();
            para.appendText("Product name:" + product.name);
            html.appendChild(para);
            para = new P();
            para.appendText("Price:" + product.price);
            html.appendChild(para);
            para = new P();
            para.appendText("Quantity:" + product.quantity);
            html.appendChild(para);
        }
        else {
            para = new P();
            para.appendText("Product not found");
            html.appendChild(para);
        }


        String response = html.write();

        // System.out.println(response);

        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

         */
    }
}
