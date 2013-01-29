package uk.ac.ebi.nmr.io;

import junit.framework.Assert;
import org.junit.Test;
import org.openscience.cdk.ChemFile;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemFile;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.tools.manipulator.ChemFileManipulator;

/**
 * Created with IntelliJ IDEA.
 * User: ldpf
 * Date: 26/09/2012
 * Time: 12:17
 * To change this template use File | Settings | File Templates.
 */
public class G09InputReaderTest {
    /**
     * test if the reader can handle a simple guassian input file, like the one for serine
     */
    @Test
    public void testReadSerine() {
        try {
            G09InputReader g09InputReader = new G09InputReader(this.getClass()
                    .getResourceAsStream("/examples/file_formats/geomMop_1.com"));
            IChemFile chemFile = new ChemFile();
            chemFile = (IChemFile) g09InputReader.read(chemFile);
            // the reader will read a sequence of atomcontainer and only the last one will have all the information
            IAtomContainer atomContainer = ChemFileManipulator.getAllAtomContainers(chemFile).get(
                    ChemFileManipulator.getAllAtomContainers(chemFile).size() - 1);

            Assert.assertEquals("Structure was interpreted differently",
                    "[H]OC(=O)C([H])(N([H])[H])C([H])([H])O[H]",
                    new SmilesGenerator().createSMILES(atomContainer).toString());
            Assert.assertEquals("Root not stored properly","#P B3LYP/6-31G*\n#  Units(Ang,Deg)",
                    ChemFileManipulator.getAllChemModels(chemFile)
                    .get(ChemFileManipulator.getAllChemModels(chemFile).size() - 1).getProperty(G09InputReader.ROOT));
            Assert.assertEquals("Header not stored properly","File generated by CCPI\n" +
                    "MM/SE Energy(kCal/mol) = -138.906880",
                    ChemFileManipulator.getAllChemModels(chemFile)
                    .get(ChemFileManipulator.getAllChemModels(chemFile).size() - 1).getProperty(G09InputReader.HEADER));

        } catch (CDKException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}