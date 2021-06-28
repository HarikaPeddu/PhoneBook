import java.io.*;
import java.util.*; // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
class PhoneBook
{
	String Name;
	String Email;
	String Number;

	public PhoneBook()
	{
		Name="N/A";
		Email="N/A";
		Number="N/A";
	}
}


class BSTNode {
	PhoneBook data;
	BSTNode left;
	BSTNode right;

	public BSTNode(PhoneBook p)
	{
		data=p;
		left=null;
		right=null;
	}

	public BSTNode insert(BSTNode root,PhoneBook p)
	{
		if(root==null)
		{
			root=new BSTNode(p);
            return root;
        }
        int result=p.Name.compareTo(root.data.Name);
        if (result<0)
            root.left = insert(root.left, p);
        else if (result > 0)
            root.right = insert(root.right, p);
        return root;
    }
	public void Inorder()
	{
		if(left !=null)
			left.Inorder();
		System.out.println(data.Name + " " + data.Number + " " + data.Email );
		if(right != null)
			right.Inorder();
	}
	public PhoneBook search(BSTNode root,String S)
	{
		int result=S.compareTo(root.data.Name);
		if(result==0)
			return root.data;
		else if(result<0 && left!=null)
			return left.search(left,S);
		else if(result>0 && right!=null)
			return right.search(right,S);
		else
			return null;
	}
	public PhoneBook minimum(BSTNode root)
	{
			if (root.left == null)
				return root.data;
			else
				return minimum(root.left);
	}

	public PhoneBook maximum(BSTNode root)
	{
			if (root.right == null)
				return root.data;
			else
				return maximum(root.right);
	}
}

class BST {
		BSTNode root=null;
		public void insert(PhoneBook p)
		{
			if(root==null)
			{
				root=new BSTNode(p);
	        }
			root.insert(root,p);
		}
		public void Search(String S)
		{
			if(root.search(root,S)==null)
				System.out.println("Not Found");
			else
				System.out.println(root.search(root,S).Name+" "+root.search(root,S).Number+" "+root.search(root,S).Email);
		}
		public void Dislpay()
		{
			if(root == null)
				return;
			else
				root.Inorder();
		}
		public void Display_First()
		{
			if(root==null)
				return;
			else
				System.out.println("First Contact: " +root.minimum(root).Name+" "+root.minimum(root).Number+" "+root.minimum(root).Email);
		}

		public void Display_Last()
		{
			if (root==null)
				return;
			else
				System.out.println("Last Contact: " +root.maximum(root).Name+" "+root.maximum(root).Number+" "+root.maximum(root).Email);
		}
}

public class Readfile
{
	public static void main(String[] args) throws FileNotFoundException
	{
		int c;
		BST b = new BST();
		File myObj = new File("contacts.txt");
      	Scanner myReader = new Scanner(myObj);
      	while (myReader.hasNextLine()) {
        	String data = myReader.nextLine();
        	String[] s=data.split(" ");
			PhoneBook temp = new PhoneBook();
			temp.Name=s[0];
			temp.Number=s[1];
			temp.Email=s[2];
			b.insert(temp);
      	}
      	myReader.close();
		Scanner in=new Scanner(System.in);
		System.out.println("PHONE DIRECTORY");
		System.out.println("1.Search");
		System.out.println("2.Display");
		System.out.println("3.Display First Contact");
		System.out.println("4.Display Last Contact");
		do {
			System.out.println("Enter Your Choice");
			int ch=in.nextInt();
			switch(ch)
			{
				case 1 : System.out.println("Enter Name to be Searched:");
				 	String s=in.next();
				 	b.Search(s);
				 	break;
				case 2 : b.Dislpay();
				 	break;
				case 3:  b.Display_First();
				 	break;
				case 4:  b.Display_Last();
				 	break;
				default : System.out.println("No such Operation");
			}
			System.out.println("Do you wish to continue? 1-Yes | 0-No");
			c=in.nextInt();
		} while(c==1);
		in.close();
	}
}
