package basics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class FileManagement 
{
	private static BufferedReader reader;
	
	public FileManagement(String file_encrypted_name, String file_decrypted_name)
	{
		File enFile = new File(file_encrypted_name);
		File deFile = new File(file_decrypted_name);
		
		if(!enFile.exists())
		{
			try 
			{
				enFile.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		if(!deFile.exists())
		{
			try 
			{
				deFile.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public SaveData readFile(String readFile)
	{	
		FileInputStream inFile = null;
				
		try 
		{
			if(!isEmpty(readFile))
			{
				inFile = new FileInputStream(readFile);
			}
			else
			{
				inFile.close();
				return null;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			ObjectInputStream input = new ObjectInputStream(inFile);
			try 
			{
				return (SaveData) input.readObject();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				addFile(new File(readFile));
				return null;
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
		}
		catch(IOException i)
		{
			i.printStackTrace();
			return null;
		}
		
		try 
		{
			inFile.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeFile(Object o, String file_name)
	{
		FileOutputStream outFile = null;
		
		try 
		{
			outFile = new FileOutputStream(file_name);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try
		{
			ObjectOutputStream output = new ObjectOutputStream(outFile);
			output.writeObject(o);
			output.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
		
		try 
		{
			outFile.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			outFile.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void addFile(File file)
	{
		if(!file.exists())
		{
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isEmpty(String checkFile)
	{
		try 
		{
			reader = new BufferedReader(new FileReader(checkFile));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			if(reader.readLine() == null)
			{
				reader.close();
				return true;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			reader.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static String printContents(String fileToPrint)
	{
		try 
		{
			reader = new BufferedReader(new FileReader(fileToPrint));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return reader.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			try 
			{
				reader.close();
			}
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			return null;
		}
	}
	
	public static void encrypt(String key, InputStream in, OutputStream out) throws Throwable
	{
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, in, out);
	}
	
	public static void decrypt(String key, InputStream in, OutputStream out) throws Throwable
	{
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, in, out);
	}
	
	public static void encryptOrDecrypt(String key, int mode, InputStream in, OutputStream out) throws Throwable
	{
		DESKeySpec keySpec = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFact = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFact.generateSecret(keySpec);
		Cipher cipher = Cipher.getInstance("DES");
		
		if(mode == Cipher.ENCRYPT_MODE)
		{
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			CipherInputStream cIn = new CipherInputStream(in, cipher);
			doCopy(cIn, out);
		}
		else if(mode == Cipher.DECRYPT_MODE)
		{
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			CipherOutputStream cOut = new CipherOutputStream(out, cipher);
			doCopy(in, cOut);
		}
	}
	
	public static void doCopy(InputStream in, OutputStream out) throws IOException
	{
		byte[] bytes = new byte[64];
		int numBytes;
		while((numBytes = in.read(bytes)) != -1)
		{
			out.write(bytes, 0, numBytes);
		}
		out.flush();
		out.close();
		in.close();
	}
}
