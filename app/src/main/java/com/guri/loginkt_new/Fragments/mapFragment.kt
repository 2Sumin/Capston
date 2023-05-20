package com.guri.loginkt_new.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.guri.loginkt_new.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class mapFragment : Fragment(com.guri.loginkt_new.R.layout.fragment_map) {


    private var _binding: FragmentMapBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root

        val mapView = MapView(activity)
        val mapViewContainer = binding.mapView as ViewGroup
        mapViewContainer.addView(mapView)


        // 중심점 변경 - 경북대 북문교차로
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.89309206218225, 128.60870156290383), true);

        // 줌 레벨 변경
        mapView.setZoomLevel(4, true);


        // 마커 찍기 함수
        fun add_marker(lat:Double, lon:Double, name:String) {

            val MARKER_POINT = MapPoint.mapPointWithGeoCoord(lat, lon)
            val marker = MapPOIItem()
            marker.itemName = name
            marker.tag = 1
            marker.mapPoint = MARKER_POINT
            marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

            marker.selectedMarkerType =
                MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mapView.addPOIItem(marker)

        }

        // 북문쪽
        add_marker(35.89432952431177, 128.61103902290492, "루트커피")
        add_marker(35.89459634525193, 128.6113013664597, "블라인드 멜론")
        // 북문 - 복치
        add_marker(35.89174551174696,128.60858572054727,"스터디카페 타스 경북대점")
        add_marker( 35.89266666237633, 128.60870624443777, "할리스 경대점")
        add_marker(35.893499071901104, 128.6089933109539, "스타벅스 경북대점")
        add_marker( 35.89308710245611, 128.60960958232278, "스테디커피 경북대점")
        add_marker(35.892688254829686, 128.60817067182884, "코펜하겐커피")
        add_marker( 35.892302449211705, 128.6079103686011, "아틀리에 빈 경북대점")
        add_marker( 35.89291616978757, 128.60810883450918, "몰타")
        add_marker( 35.8942209625012, 128.60999579285038, "커피베이 경북대북문점")
        add_marker( 35.89486952491348, 128.61108983600533, "커피장인")
        add_marker( 35.89486952491348, 128.61108983600533, "실버라운지")
        add_marker( 35.89429865768909, 128.61065299442467, "초이크")

        // 정문쪽
        add_marker(35.88472103980328, 128.61363043068337, "해비치 커피")
        add_marker(35.88442810547112, 128.61343402403512, "더블유 컬렉션")
        add_marker(35.88448973018738, 128.6139446518626, "빽다방")
        add_marker(35.88529507859065, 128.61356234917326, "커피 셀러")
        add_marker(35.88916367134269, 128.61671058585122, "목로 카페")
        add_marker(35.886161096408955, 128.61027111150923, "더쪽")
        add_marker(35.886283265653674, 128.60707107602758, "콘크리트")
        add_marker(35.88586437472183, 128.61008792504094, "키아누")
        add_marker(35.888919430177616, 128.6169138116185, "H커피")
        add_marker(35.88476924606574, 128.61084532458645, "제리커피")


       return view
    }





}