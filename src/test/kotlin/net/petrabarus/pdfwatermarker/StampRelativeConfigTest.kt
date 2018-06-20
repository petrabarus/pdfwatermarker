package net.petrabarus.pdfwatermarker

import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.DataProviders.`$$`
import com.tngtech.java.junit.dataprovider.DataProviders.`$`
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class StampRelativeConfigTest {

    companion object {

        @DataProvider
        @JvmStatic
        fun constructDataProvider(): Array<Array<Any>> = `$$`(
                // @formatter:off
                `$`( 0,  0,  0)
                // @formatter:on
        )
    }

    @Test
    @UseDataProvider(value = "constructDataProvider")
    fun construct(a: Int, b: Int, c: Int) {
        print(a)
        print(b)
        print(c)
    }
}
