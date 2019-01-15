import java.nio.file.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Throwable;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.ErrorWarning;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.parser.CompModule;
import edu.mit.csail.sdg.alloy4compiler.parser.CompUtil;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;
import edu.mit.csail.sdg.alloy4viz.VizGUI;

public final class Run_hw2_directedtree {
    // Given a set of models as strings, runs against hard coded specifications and provides feedback
    public static void main(String[] args) throws Err {
        String reveal = "";
        String out = "";
        final String[] ocspecs = {};
        final String goldspec = "hw2_directedtree/gold.als";
        final String[] ucspecs = {"hw2_directedtree/not-acyclic.als", "hw2_directedtree/not-connected.als", "hw2_directedtree/not-injective.als"};
        boolean[] ocs = new boolean[ocspecs.length];
        boolean[] ucs = new boolean[ucspecs.length];
        String uniqueid = args[0];
        // For each good / bad example...
        for(int e=1; e < args.length; e++) {
            String example = args[e];
            if(!example.trim().isEmpty()) {
                boolean good = example.contains("expect 1");
                Boolean consistent = check(uniqueid, goldspec, example, good);
                if(consistent==true) {
                    if(good) {
                        for(int i=0; i<ocspecs.length; i++) {
                            if(check(uniqueid, ocspecs[i], example, false)==true) {
                                ocs[i] = true;
                                reveal += " "+ocspecs[i];
                            }
                        }
                    } else {
                        for(int i=0; i<ucspecs.length; i++) {
                            if(check(uniqueid, ucspecs[i], example, true)==true) {
                                ucs[i] = true;
                                reveal += " "+ucspecs[i];
                            }
                        }
                    }
                } else {
                    out += "Example #"+e+" is inconsistent with the gold-standard spec.\n";
                }
            }
        }
        int ocsnum = 0;
        for(boolean caught : ocs) if(caught) ocsnum++;
        out += "Avoided "+ocsnum+" of "+ocs.length+" over-constrained specs.\n";
        int ucsnum = 0;
        for(boolean caught : ucs) if(caught) ucsnum++;
        out += "Caught "+ucsnum+" of "+ucs.length+" under-constrained specs.\n";
        System.out.println(reveal+"\n"+out);
        return;
    }
    // Given a single spec, example, and expected SAT result, produces feedback
    public static Boolean check(String uniqueid, String spec, String example, boolean expected) throws Err {
        // Add example command to base spec in a temp file
        String espec = "temp/"+uniqueid+".als";
        try {
            Files.copy(Paths.get(spec), Paths.get(espec), StandardCopyOption.REPLACE_EXISTING);
            example = example.replaceAll("@", "\n");
            Files.write(Paths.get(espec), example.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("INTERNAL ERROR: IO Error appending Example to Spec. Aborting.");
            System.out.println(e);
            System.exit(1);
        }
        // Load spec
        A4Reporter rep = new A4Reporter() {
            @Override public void warning(ErrorWarning msg) {
                //System.out.println(("Relevance Warning:\n"+(msg.toString().trim())+"\n\n");
                //System.out.flush();
            }
        };
        A4Options options = new A4Options();
        options.solver = A4Options.SatSolver.SAT4J;
        CompModule world = null;
        try {
            world = CompUtil.parseEverything_fromFile(rep, null, espec);
        }
        catch (Exception e)
        {
            System.out.println("INTERNAL ERROR: Syntax Error in Spec. Aborting.");
            System.exit(1);
        }
        // Run example command
        Boolean result = null;
        for (Command command: world.getAllCommands()) {
            A4Solution ans = TranslateAlloyToKodkod.execute_command(rep, world.getAllReachableSigs(), command, options);
            result = (expected == ans.satisfiable());
        }
        return result;
    }  
}
