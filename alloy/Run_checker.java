import java.nio.file.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

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

public final class Run_checker {
    // Given a set of models as strings, runs against hard coded specifications and provides feedback
    public static void main(String[] args) throws Err {
        String reveal = "";
        String out = "";
        // Load the proper specs depending on the task
        String taskid = args[0];
        List<String> ocspecs = null;
        String goldspec = null;
        List<String> ucspecs = null;
        if (taskid.equals("hw2_directedtree_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_directedtree_easy/binary-only.als"));
            goldspec = "hw2_directedtree_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_directedtree_easy/not-acyclic.als", "hw2_directedtree_easy/not-connected.als", "hw2_directedtree_easy/not-injective.als"));
        }
        else if (taskid.equals("hw2_directedtree_hard")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_directedtree_hard/binary-only.als"));
            goldspec = "hw2_directedtree_hard/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_directedtree_hard/not-acyclic.als", "hw2_directedtree_hard/not-connected.als", "hw2_directedtree_hard/not-injective.als"));
        }
        else if (taskid.equals("hw2_ring_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_ring_easy/disallow-one.als"));
            goldspec = "hw2_ring_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_ring_easy/not-onepointer.als", "hw2_ring_easy/not-reachable.als"));
        }
        else if (taskid.equals("hw2_ring_hard")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_ring_hard/disallow-one.als"));
            goldspec = "hw2_ring_hard/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_ring_hard/not-onepointer.als", "hw2_ring_hard/not-reachable.als"));
        }
        else if (taskid.equals("hw2_spanningtree_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_spanningtree_easy/degree-two-or-less.als"));
            goldspec = "hw2_spanningtree_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_spanningtree_easy/not-distinct.als", "hw2_spanningtree_easy/not-spanning.als", "hw2_spanningtree_easy/not-undirectedtree.als"));
        }
        else if (taskid.equals("hw2_spanningtree_hard")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_spanningtree_hard/degree-two-or-less.als"));
            goldspec = "hw2_spanningtree_hard/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_spanningtree_hard/not-distinct.als", "hw2_spanningtree_hard/not-spanning.als", "hw2_spanningtree_hard/not-undirectedtree.als"));
        }
        else if (taskid.equals("hw2_undirectedtree_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_undirectedtree_easy/no-more-than-three.als"));
            goldspec = "hw2_undirectedtree_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_undirectedtree_easy/not-acyclic.als", "hw2_undirectedtree_easy/not-connected.als", "hw2_undirectedtree_easy/not-minimally-connected.als", "hw2_undirectedtree_easy/not-symmetric.als"));
        }
        else if (taskid.equals("hw2_undirectedtree_hard")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw2_undirectedtree_hard/no-more-than-three.als"));
            goldspec = "hw2_undirectedtree_hard/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw2_undirectedtree_hard/not-acyclic.als", "hw2_undirectedtree_hard/not-connected.als", "hw2_undirectedtree_hard/not-minimally-connected.als", "hw2_undirectedtree_hard/not-symmetric.als"));
        }
        else if (taskid.equals("timdemo")) {
            ocspecs = new ArrayList<String>(Arrays.asList("timdemo/lone-pair.als"));
            goldspec = "timdemo/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("timdemo/missing-partner.als", "timdemo/not-symmetric.als", "timdemo/no-max.als"));
        }
        else if (taskid.equals("hw3_goatswolves_initial_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_initial_easy/no-animal.als"));
            goldspec = "hw3_goatswolves_initial_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_initial_easy/some-far.als", "hw3_goatswolves_initial_easy/boat-far.als", "hw3_goatswolves_initial_easy/eating.als"));
        }
        else if (taskid.equals("hw3_goatswolves_final_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_final_easy/no-animal.als"));
            goldspec = "hw3_goatswolves_final_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_final_easy/some-near.als", "hw3_goatswolves_final_easy/boat-near.als", "hw3_goatswolves_final_easy/eating.als"));
        }
        else if (taskid.equals("hw3_goatswolves_event_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_event_easy/equal-goat-wolf.als"));
            goldspec = "hw3_goatswolves_event_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw3_goatswolves_event_easy/invalid-transition.als", "hw3_goatswolves_event_easy/eating.als", "hw3_goatswolves_event_easy/floating.als"));
        }
        else if (taskid.equals("hw4_statemachine_deterministic_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_deterministic_easy/one_succ.als"));
            goldspec = "hw4_statemachine_deterministic_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_deterministic_easy/no_initial.als", "hw4_statemachine_deterministic_easy/nondeterministic.als"));
        }
        else if (taskid.equals("hw4_statemachine_reachable_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_reachable_easy/directly_reachable.als"));
            goldspec = "hw4_statemachine_reachable_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_reachable_easy/no_initial.als", "hw4_statemachine_reachable_easy/unreachable.als"));
        }
        else if (taskid.equals("hw4_statemachine_deadlock_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_deadlock_easy/smallest_deadlock.als"));
            goldspec = "hw4_statemachine_deadlock_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_deadlock_easy/no_initial.als", "hw4_statemachine_deadlock_easy/alive.als"));
        }
        else if (taskid.equals("hw4_statemachine_livelock_easy")) {
            ocspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_livelock_easy/l_not_directly_reachable_from_cycle.als"));
            goldspec = "hw4_statemachine_livelock_easy/gold.als";
            ucspecs = new ArrayList<String>(Arrays.asList("hw4_statemachine_livelock_easy/no_initial.als", "hw4_statemachine_livelock_easy/cycle_unreachable.als", "hw4_statemachine_livelock_easy/cycle_contains_l.als", "hw4_statemachine_livelock_easy/l_unreachable_from_cycle.als"));
        }
        else {
            System.out.println("INTERNAL ERROR: Task <"+taskid+"> not recognized. Aborting.");
            System.exit(1);
        }
        boolean[] ocs = new boolean[ocspecs.size()];
        boolean[] ucs = new boolean[ucspecs.size()];
        String uniqueid = args[1];
        // For each good / bad example...
        for(int e=2; e < args.length; e++) {
            String example = args[e];
            if(!example.trim().isEmpty()) {
                boolean good = example.contains("expect 1");
                Boolean consistent = check(uniqueid, goldspec, example, good);
                if(consistent==true) {
                    if(good) {
                        for(int i=0; i<ocspecs.size(); i++) {
                            if(check(uniqueid, ocspecs.get(i), example, false)==true) {
                                ocs[i] = true;
                                reveal += " "+ocspecs.get(i);
                            }
                        }
                    } else {
                        for(int i=0; i<ucspecs.size(); i++) {
                            if(check(uniqueid, ucspecs.get(i), example, true)==true) {
                                ucs[i] = true;
                                reveal += " "+ucspecs.get(i);
                            }
                        }
                    }
                } else {
                    out += "Example #"+(e-1)+" is inconsistent with the gold-standard spec.\n";
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
