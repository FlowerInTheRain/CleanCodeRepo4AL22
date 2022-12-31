package test;


import com.cleancode.cleancodeapi.enums.cardpackdistributionsenum.DiamondPackCardRarityDistributionEnum;
import com.cleancode.cleancodeapi.enums.rarities.RaritiesEnum;
import org.junit.Assert;
import org.junit.Test;

public class test {

    @Test
    public void Common(){
        Assert.assertEquals(DiamondPackCardRarityDistributionEnum.getByProbability(30L), RaritiesEnum.COMMON);
    }

    @Test
    public void Unique(){
        Assert.assertEquals(DiamondPackCardRarityDistributionEnum.getByProbability(100L), RaritiesEnum.UNIQUE);
    }
}
