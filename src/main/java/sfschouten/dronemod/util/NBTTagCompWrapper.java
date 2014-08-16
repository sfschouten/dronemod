package sfschouten.dronemod.util;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.io.output.ByteArrayOutputStream;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Allows for easy conversion.
 * @author Stefan
 *
 */
public class NBTTagCompWrapper {

	private NBTTagCompound tagComp;
	
	public NBTTagCompWrapper(NBTTagCompound tagComp){
		this.tagComp = tagComp;
	}
	
	public String toProperString(){
		ByteArrayOutputStream baOutStrem = new ByteArrayOutputStream();
		DataOutputStream strem = new DataOutputStream(baOutStrem);
		Method m = ReflectionHelper.findMethod(NBTTagCompound.class, tagComp, new String[]{"write"}, new Class[]{DataOutput.class});
		try {
			m.invoke(tagComp, strem);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		String result = baOutStrem.toString();
		return result;
	}
	
	public NBTTagCompound fromProperString(String s){
		ByteArrayInputStream baInStrem = new ByteArrayInputStream(s.getBytes());
		DataInputStream strem = new DataInputStream(baInStrem);
		Method m = ReflectionHelper.findMethod(NBTTagCompound.class, this.tagComp, new String[]{"load"}, new Class[]{DataInput.class, int.class});
		try {
			m.invoke(this.tagComp, new Object[]{strem, 0});
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return tagComp;
	}
}
