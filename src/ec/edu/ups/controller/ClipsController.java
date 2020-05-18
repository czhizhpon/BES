package ec.edu.ups.controller;

import net.sf.clipsrules.jni.CLIPSLoadException;
import net.sf.clipsrules.jni.Environment;

public class ClipsController {

	private Environment clips;

	public Environment getClips() {
		return clips;
	}

	public void setClips(Environment clips) {
		this.clips = clips;
	}

	public ClipsController(String knowloadgePath) throws CLIPSLoadException {
		clips = new Environment();
		clips.load(knowloadgePath);
	}
}
