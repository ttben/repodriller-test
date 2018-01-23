package fr.cnrs.i3s.sparks.composition.merge.study;

import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.GitRepository;
import org.repodriller.scm.SCM;
import org.repodriller.scm.SCMRepository;

import java.util.List;

public class MergeCommitVisitor implements CommitVisitor {
    public void process(SCMRepository scmRepository, Commit commit, PersistenceMechanism persistenceMechanism) {
        if (commit.isMerge()) {
            if (commit.getBranches().isEmpty()) {
                System.err.println(commit.getHash());
                // THIS SHOULD NOT HAPPEN
                persistenceMechanism.write(commit.getHash());
            }
        }
    }
}