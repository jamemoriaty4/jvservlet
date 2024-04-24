package ss1.demo.jspservlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Home", value = "/todo")
public class TodoServlet extends HttpServlet {
    private static List<Todo> todoList;
    @Override
    public void init()  {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,"đi học","đi học thêm toán",false));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // nhận cac request theo phuong thuc GET : Sử dụng đường dẫn, thẻ <a> , thẻ <form method ="GET">
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "GETALL":
                    // hiển thị danh sách sp
                    showListTodo(request,response);
                    break;
                case "DELETE":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    deleteById(request,response,idDelete);
                    break;
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    showTodoEdit(request,response,idEdit);
                    break;
                case "ADD":
                    request.getRequestDispatcher("/add.jsp").forward(request,response);
                    break;
                default:
                    System.err.println("ko phù hơp");
            }
        }
    }

    protected void showListTodo(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("list",todoList);
        // gửi sang trang jsp
        req.getRequestDispatcher("/list.jsp").forward(req,res);
    }
    protected void showTodoEdit(HttpServletRequest req,HttpServletResponse res,int id) throws ServletException, IOException {
        Todo todoEdit = todoList.stream().filter(t->t.getId()==id).findFirst().orElse(null);
        req.setAttribute("todo",todoEdit);
        // gửi sang trang jsp
        req.getRequestDispatcher("/edit.jsp").forward(req,res);
    }
    protected void deleteById(HttpServletRequest req,HttpServletResponse res,int id) throws ServletException,IOException{
        Todo todo = todoList.stream().filter(t->t.getId()==id).findFirst().orElse(null);
        if (todo!=null){
            todoList.remove(todo);
        }
        // hiển thị lại danh sách
        showListTodo(req, res);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // nhận các request gửi theo post : <form method="post">
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action!=null){
            switch (action){
                case "UPDATE":
                    handleUpdateTodo(request,response);
                    break;
                case "ADD":
                    handleAddTodo(request,response);
                    break;
            }
        }
    }

    private  void handleUpdateTodo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int idEdit = Integer.parseInt(request.getParameter("id"));
        String titleUp = request.getParameter("title");
        String contentUp = request.getParameter("content");
        boolean statusUp = Boolean.parseBoolean(request.getParameter("status"));
        Todo todoUpdate = new Todo(idEdit,titleUp,contentUp,statusUp);
        Todo todoOld = todoList.stream().filter(t->t.getId()==idEdit).findFirst().orElse(null);
        todoList.set(todoList.indexOf(todoOld),todoUpdate);
        showListTodo(request,response);
    }
    private  void handleAddTodo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        int idNew= getNewId();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Todo todoNew = new Todo(idNew,title,content,false);
        todoList.add(todoNew);
        showListTodo(request,response);
    }

    private int getNewId(){
        int idMax = todoList.stream().mapToInt(Todo::getId).max().orElse(0);
        return idMax+1;

    }


}