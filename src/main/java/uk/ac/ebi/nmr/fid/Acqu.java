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

package uk.ac.ebi.nmr.fid;

/**
 * Data structure for the acquisition parameters
 *
 * @author  Luis F. de Figueiredo
 *
 * User: ldpf
 * Date: 14/01/2013
 * Time: 14:01
 *
 */
public class Acqu {


    //TODO use an enum for parameters that have a limited set of options such as aquisition mode
    private double transmiterFreq;               //sfo1
    private double decoupler1Freq;               //sfo2
    private double decoupler2Feq;                //sfo3
    private double freqOffset;                   //o1 (Hz)
    private double spectralFrequency;            //BF1 (Hz)
    private double spectralWidth;                //sw            sweep width (ppm)
    private int aquiredPoints;                   //td            acquired points (real + imaginary)
    private int dspDecimation;                   //decim         DSP decimation factor
    private int dspFirmware;                     //dspfvs        DSP firmware version
    private double dspGroupDelay;                //grpdly        DSP group delay
    private int byteOrder;                       //bytorda       byte order
    private int filterType;                      //digmod        filter type
    private int numberOfScans;                   //ns            number of scans
    private boolean integerType;                 //dtypa         data type (0 -> 32 bit int, 1 -> 64 bit double)
    private String pulseProgram;                 //pulprog       pulse program
    private String observedNucleus;              //nuc1          observed nucleus
    private String instrumentName;               //instrum       instrument name
    private String solvent;                      //solvent       solvent
    private String probehead;                    //probehead     probehead
    private String origin;                       //origin        origin
    private String owner;                        //owner         owner
    private AcquisitionMode acquisitionMode;      //aq_mod        acquisition mode
    private FidData fidType;                     //fid_type      define in class data_par
    private Spectrometer spectrometer;

    public enum Spectrometer {BRUKER, VARIAN, JEOL}

    public Acqu(Spectrometer spectrometer) {
        this.spectrometer=spectrometer;
    }

    public double getTransmiterFreq() {
        return transmiterFreq;
    }

    public void setTransmiterFreq(double transmiterFreq) {
        this.transmiterFreq = transmiterFreq;
    }

    public double getDecoupler1Freq() {
        return decoupler1Freq;
    }

    public void setDecoupler1Freq(double decoupler1Freq) {
        this.decoupler1Freq = decoupler1Freq;
    }

    public double getDecoupler2Feq() {
        return decoupler2Feq;
    }

    public void setDecoupler2Feq(double decoupler2Feq) {
        this.decoupler2Feq = decoupler2Feq;
    }

    public double getFreqOffset() {
        return freqOffset;
    }

    public void setFreqOffset(double freqOffset) {
        this.freqOffset = freqOffset;
    }

    public double getSpectralWidth() {
        return spectralWidth;
    }

    public void setSpectralWidth(double spectralWidth) {
        this.spectralWidth = spectralWidth;
    }

    public int getAquiredPoints() {
        return aquiredPoints;
    }

    public void setAquiredPoints(int aquiredPoints) {
        this.aquiredPoints = aquiredPoints;
    }

    public int getDspDecimation() {
        return dspDecimation;
    }

    public void setDspDecimation(int dspDecimation) {
        this.dspDecimation = dspDecimation;
    }

    public int getDspFirmware() {
        return dspFirmware;
    }

    public void setDspFirmware(int dspFirmware) {
        this.dspFirmware = dspFirmware;
    }

    public double getDspGroupDelay() {
        return dspGroupDelay;
    }

    public void setDspGroupDelay(double dspGroupDelay) {
        this.dspGroupDelay = dspGroupDelay;
    }

    public int getByteOrder() {
        return byteOrder;
    }

    public void setByteOrder(int byteOrder) {
        this.byteOrder = byteOrder;
    }

    public AcquisitionMode getAcquisitionMode() {
        return acquisitionMode;
    }

    public void setAcquisitionMode(int acquisitionMode) {
        for (AcquisitionMode mode : AcquisitionMode.values())
            if (mode.type == acquisitionMode)
                this.acquisitionMode = mode;
    }
    public void setAcquisitionMode(AcquisitionMode mode) {
        this.acquisitionMode=mode;

    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getNumberOfScans() {
        return numberOfScans;
    }

    public void setNumberOfScans(int numberOfScans) {
        this.numberOfScans = numberOfScans;
    }

    public String getPulseProgram() {
        return pulseProgram;
    }

    public void setPulseProgram(String pulseProgram) {
        this.pulseProgram = pulseProgram;
    }

    public String getObservedNucleus() {
        return observedNucleus;
    }

    public void setObservedNucleus(String observedNucleus) {
        this.observedNucleus = observedNucleus;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public boolean is32Bit() {
        return integerType;
    }

    public void set32Bit(boolean is32Bit) {
        fidType=(is32Bit)?FidData.INT32:FidData.DOUBLE;
        this.integerType = is32Bit;
    }

    public String getSolvent() {
        return solvent;
    }

    public void setSolvent(String solvent) {
        this.solvent = solvent;
    }

    public String getProbehead() {
        return probehead;
    }

    public void setProbehead(String probehead) {
        this.probehead = probehead;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public FidData getFidType() {
        return fidType;
    }

    public enum AcquisitionMode {
        SEQUENTIAL      (1),
        SIMULTANIOUS    (2),
        DISP            (3);

        private final int type;

        private AcquisitionMode(int type){
            this.type=type;
        }

        private double getType(){return type;};
    }

    public enum FidData {
        INT32       (1),
        DOUBLE      (2),
        FLOAT       (3),
        INT16       (4);

        private final int type;

        private FidData(int type){
            this.type=type;
        }

        private double getType(){return type;};
    }

    public double getSpectralFrequency() {
        return spectralFrequency;
    }

    public void setSpectralFrequency(double spectralFrequency) {
        this.spectralFrequency = spectralFrequency;
    }

    public Spectrometer getSpectrometer() {
        return spectrometer;
    }
}
