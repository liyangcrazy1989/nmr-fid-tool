/*
 * Copyright (c) 2013. EMBL, European Bioinformatics Institute
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uk.ac.ebi.nmr.fid.io;

import uk.ac.ebi.nmr.fid.Acqu;
import uk.ac.ebi.nmr.fid.Proc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reader for Bruker's proc and procs files
 *
 * @author Luis F. de Figueiredo
 *
 * User: ldpf
 * Date: 14/01/2013
 * Time: 14:12
 *
 */
public class BrukerProcReader implements ProcReader {

    private File procFile;
    private static Acqu acquisition;
    // parameters from proc
    private final static Pattern REGEXP_SI = Pattern.compile("\\#\\#\\$SI= (\\d+\\.\\d+)"); //transform size (complex)
    private final static Pattern REGEXP_SF = Pattern.compile("\\#\\#\\$SF= (\\d+\\.\\d+)"); //frequency of 0 ppm (???)
    private final static Pattern REGEXP_GB = Pattern.compile("\\#\\#\\$GB= (\\d+\\.\\d+)"); //GB-factor (Gain?)
    private final static Pattern REGEXP_LB = Pattern.compile("\\#\\#\\$LB= (\\d+)"); //line broadening
    private final static Pattern REGEXP_WDW = Pattern.compile("\\#\\#\\$WDW= (\\d+)"); //window function type
    private final static Pattern REGEXP_PH_MODE = Pattern.compile("\\#\\#\\$PH\\_mod= (\\d+)"); //phasing type
    private final static Pattern REGEXP_PHC0 = Pattern.compile("\\#\\#\\$PHC0= (-?\\d+\\.\\d+)"); //zero order phase
    private final static Pattern REGEXP_PHC1 = Pattern.compile("\\#\\#\\$PHC1= (-?\\d+\\.\\d+)"); //first order phase
    private final static Pattern REGEXP_SSB = Pattern.compile("\\#\\#\\$SSB= (-?\\d+\\.\\d+)"); //sine bell shift
    private final static Pattern REGEXP_MC2 = Pattern.compile("\\#\\#\\$MC2= (\\d+)"); //F1 detection mode

    

    public BrukerProcReader(File procFile, Acqu acquisition) {
        this.procFile=procFile;
        this.acquisition=acquisition;
    }

    public BrukerProcReader(String filename) throws FileNotFoundException {
        this.procFile = new File(filename);
        this.acquisition=acquisition;
    }

    @Override
    public Proc read() throws IOException{
        Proc processing = null;
        try {
            processing = new Proc(acquisition);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Matcher matcher;
//        Acqu acquisition = new Acqu(Acqu.Spectrometer.BRUKER);
        BufferedReader input = new BufferedReader(new FileReader(procFile));
        String line = input.readLine();
        while (input.ready() && (line != null)) {
            if(REGEXP_SI.matcher(line).find()){
                matcher=REGEXP_SI.matcher(line);
                matcher.find();
                processing.setTransformSize(Integer.parseInt(matcher.group(1)));
            }
            if(REGEXP_WDW.matcher(line).find()){
                matcher=REGEXP_WDW.matcher(line);
                matcher.find();
                processing.setWindowFunctionType(Integer.parseInt(matcher.group(1)));
            }
            if(REGEXP_PH_MODE.matcher(line).find()){
                matcher=REGEXP_PH_MODE.matcher(line);
                matcher.find();
                processing.setPhasingType(Integer.parseInt(matcher.group(1)));
            }
            if(REGEXP_MC2.matcher(line).find()){
                matcher=REGEXP_MC2.matcher(line);
                matcher.find();
                processing.setF1DetectionMode(Integer.parseInt(matcher.group(1)));
            }
            if (REGEXP_SF.matcher(line).find()){
                matcher = REGEXP_SF.matcher(line);
                matcher.find();
                processing.setZeroFrequency(Double.parseDouble(matcher.group(1)));
            }
            if(REGEXP_LB.matcher(line).find()){
                matcher=REGEXP_LB.matcher(line);
                matcher.find();
                processing.setLineBroadening(Double.parseDouble(matcher.group(1)));
            }
            if (REGEXP_GB.matcher(line).find()){
                matcher = REGEXP_GB.matcher(line);
                matcher.find();
                processing.setGbFactor(Double.parseDouble(matcher.group(1)));
            }
            if (REGEXP_PHC0.matcher(line).find()){
                matcher = REGEXP_PHC0.matcher(line);
                matcher.find();
                processing.setZeroOrderPhase(Double.parseDouble(matcher.group(1)));
            }
            if (REGEXP_PHC1.matcher(line).find()){
                matcher = REGEXP_PHC1.matcher(line);
                matcher.find();
                processing.setFirstOrderPhase(Double.parseDouble(matcher.group(1)));
            }
            if (REGEXP_SSB.matcher(line).find()){
                matcher = REGEXP_SSB.matcher(line);
                matcher.find();
                processing.setSsb(Double.parseDouble(matcher.group(1)));
            }
            line = input.readLine();
        }
        input.close();
        return processing;
    }
}