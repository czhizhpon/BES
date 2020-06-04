package ec.edu.ups.controller;

import java.io.File;

import net.sf.clipsrules.jni.CLIPSException;
import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.MultifieldValue;

public class ClipsController {

	private Environment clips;

	public ClipsController() {

	}

	public ClipsController(String knowloadgePath) throws CLIPSException {
		clips = new Environment();
		loadKnowlege(knowloadgePath);
		File f = new File(knowloadgePath);
		if (f.exists()) {
			System.out.println("SI HAY");
		} else {
			System.out.println("PATH: " + knowloadgePath);
		}
	}

	public Environment getClips() {
		return clips;
	}

	public void setClips(Environment clips) {
		this.clips = clips;
	}

	public void loadKnowlege(String knowloadgePath) throws CLIPSException {
		clips.load(knowloadgePath);
		clips.reset();
	}

	public void runRules() throws CLIPSException {
		clips.run();
	}

	public MultifieldValue getAResult(String query) throws CLIPSException {
		return (MultifieldValue) clips.eval(query);
	}

	public String getAGlobalVariable(String query) throws CLIPSException {
		String result = clips.eval(query).toString();
		result = result.substring(1, result.length() - 1);
		return result;
	}

	public boolean saveInstances(String query) throws CLIPSException {
		clips.eval(query);
		return true;
	}

	public int getLenInstance(String className) throws CLIPSException {
		String query = "(find-all-instances((?c " + className + ")) TRUE)";
		return getAResult(query).size();
	}
}
